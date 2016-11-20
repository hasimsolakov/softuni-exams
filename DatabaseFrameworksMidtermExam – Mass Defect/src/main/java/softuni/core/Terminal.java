package softuni.core;

import jdk.internal.org.xml.sax.SAXException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.constants.Messages;
import softuni.dto.json.export.*;
import softuni.dto.json.input.*;
import softuni.dto.xml.AnomaliesImportDto;
import softuni.dto.xml.AnomalyWithVictimImportDto;
import softuni.dto.xml.VictimImportDto;
import softuni.dto.xml.export.AnomaliesExportXMLDto;
import softuni.dto.xml.export.AnomalyExportXMLDto;
import softuni.dto.xml.export.VictimExportXMLDto;
import softuni.entities.*;
import softuni.io.Writer;
import softuni.parser.JSONParser;
import softuni.parser.XMLParser;
import softuni.service.*;
import softuni.dto.json.export.TeleportPlanetExportDto;

import javax.xml.bind.JAXBException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class Terminal implements CommandLineRunner {

    private static final String INPUT_JSON_FILES_FOLDER_URI = "src/main/resources/files/input/json/";
    private static final String INPUT_XML_FILES_FOLDER_URI = "src/main/resources/files/input/xml/";
    private static final String OUTPUT_JSON_FILES_FOLDER_URI = "src/main/resources/files/output/json/";
    private static final String OUTPUT_XML_FILES_FOLDER_URI = "src/main/resources/files/output/xml/";
    private static final String INVALID_DATA_ERROR = "Error: Invalid data.";

    @Autowired
    private JSONParser jsonParser;

    @Autowired
    private XMLParser xmlParser;

    @Autowired
    private Writer writer;

    @Autowired
    private SolarSystemService solarSystemService;

    @Autowired
    private StarService starService;

    @Autowired
    private PlanetService planetService;

    @Autowired
    private PersonService personService;

    @Autowired
    private AnomalyService anomalyService;

    @Override
    public void run(String... strings) throws Exception {
       this.importDataFromJson();
        this.importingDataFromXML();
        this.exportingDataToJson();
        this.exportPeopleWhichHaveNotBeenVictims();
        this.exportAnomalyWhichAffectedTheMostPeopleToJson();
        this.exportingDataToXMl();
    }

    private void importSolarSystemDataFromJson() {
        SolarSystemImportDto[] populatedSolarSystemDtos = this.jsonParser.readFromJSON(SolarSystemImportDto[].class, INPUT_JSON_FILES_FOLDER_URI + "solar-systems.json");
        for (SolarSystemImportDto solarSystemImportDto  : populatedSolarSystemDtos) {
            try {
                SolarSystem solarSystem = new SolarSystem(solarSystemImportDto.getName());
                this.solarSystemService.save(solarSystem);
                this.writer.printLine(String.format(Messages.SUCCESSFULLY_IMPORTED_MESSAGE, "Solar System", solarSystem.getName()));
            } catch (IllegalArgumentException argumentException) {
                this.writer.printLine(INVALID_DATA_ERROR);
            }
        }
    }

    private void importStarsDataFromJson() {
        StarImportDto [] populatedStarDtOs = this.jsonParser.readFromJSON(StarImportDto[].class, INPUT_JSON_FILES_FOLDER_URI + "stars.json");

        for (StarImportDto starImportDTO : populatedStarDtOs) {
            try {
                String name = starImportDTO.getName();
                SolarSystem solarSystem = this.solarSystemService.findByName(starImportDTO.getSolarSystem());
                Star star = new Star(name, solarSystem);
                this.starService.save(star);
                this.writer.printLine(String.format(Messages.SUCCESSFULLY_IMPORTED_MESSAGE, "Star", star.getName()));
            } catch (IllegalArgumentException e) {
                this.writer.printLine(INVALID_DATA_ERROR);
            }
        }
    }

    private void importPlanetsDataFromJson() {
        PlanetImportDto[] populatedPlanetDtOs = this.jsonParser.readFromJSON(PlanetImportDto[].class, INPUT_JSON_FILES_FOLDER_URI + "planets.json");

        for (PlanetImportDto planetImportDTO : populatedPlanetDtOs) {
            try {
                String planetName = planetImportDTO.getName();
                SolarSystem solarSystem = this.solarSystemService.findByName(planetImportDTO.getSolarSystem());
                Star sun = this.starService.findByName(planetImportDTO.getSun());
                Planet planet = new Planet(planetName, sun, solarSystem);
                this.planetService.save(planet);
                this.writer.printLine(String.format(Messages.SUCCESSFULLY_IMPORTED_MESSAGE, "Planet", planet.getName()));
            } catch (IllegalArgumentException ex) {
                this.writer.printLine(INVALID_DATA_ERROR);
            }
        }
    }

    private void importPersonsDataFromJson() {
        PersonImportDto[] populatedPersonDtOs = this.jsonParser.readFromJSON(PersonImportDto[].class, INPUT_JSON_FILES_FOLDER_URI + "persons.json");

        for (PersonImportDto personImportDTO : populatedPersonDtOs) {
            try {
                String personName = personImportDTO.getName();
                Planet homePlanet = this.planetService.findByName(personImportDTO.getHomePlanet());
                Person person  = new Person(personName, homePlanet, new HashSet<>());
                this.personService.save(person);
                this.writer.printLine(String.format(Messages.SUCCESSFULLY_IMPORTED_MESSAGE, "Person", person.getName()));
            } catch (IllegalArgumentException ex) {
                this.writer.printLine(INVALID_DATA_ERROR);
            }
        }
    }

    private void importAnomalyDataFromJson() {
        AnomalyImportDto[] populatedAnomalyDtOs = this.jsonParser.readFromJSON(AnomalyImportDto[].class, INPUT_JSON_FILES_FOLDER_URI + "anomalies.json");

        for (AnomalyImportDto anomalyImportDTO : populatedAnomalyDtOs) {
            try {
                Planet originPlanet = this.planetService.findByName(anomalyImportDTO.getOriginPlanet());
                Planet teleportPlanet = this.planetService.findByName(anomalyImportDTO.getTeleportPlanet());
                Anomaly anomaly = new Anomaly(originPlanet, teleportPlanet, new HashSet<>());
                this.anomalyService.save(anomaly);
                this.writer.printLine(Messages.SUCCESSFULLY_IMPORTED_ANOMALY_MESSAGE);
            } catch (IllegalArgumentException ex) {
                this.writer.printLine(INVALID_DATA_ERROR);
            }
        }
    }

    private void importAnomalyVictimsDataFromJson() {
        try {
            AnomalyVictimsImportDto[] populatedAnomalyVictimsDtOs = this.jsonParser.readFromJSON(AnomalyVictimsImportDto[].class, INPUT_JSON_FILES_FOLDER_URI + "anomaly-victims.json");

            if (populatedAnomalyVictimsDtOs != null) {

                for (AnomalyVictimsImportDto anomalyVictimsImportDTO : populatedAnomalyVictimsDtOs) {
                    try {
                        Anomaly anomaly = this.anomalyService.findById(anomalyVictimsImportDTO.getId());
                        Person person = this.personService.findByName(anomalyVictimsImportDTO.getPerson());
                        anomaly.getVictims().add(person);
                        this.anomalyService.save(anomaly);
                    }catch (IllegalArgumentException ex) {
                        this.writer.printLine(INVALID_DATA_ERROR);
                    }
                }
            }
        } catch (IllegalArgumentException ignored) {
            this.writer.printLine(INVALID_DATA_ERROR);
        }
    }

    // All data importing from Json files
    private void importDataFromJson() {
        this.importSolarSystemDataFromJson();

        this.importStarsDataFromJson();

        this.importPlanetsDataFromJson();

        this.importPersonsDataFromJson();

        this.importAnomalyDataFromJson();

        this.importAnomalyVictimsDataFromJson();
    }

    private void exportingDataToJson() {
        List<Planet> allPlanets = this.planetService.findAll();
        List<Planet> planetsWithoutOriginatedAnomaly = allPlanets
                .stream()
                .filter(planet -> planet.getOriginatedAnomalies().size() == 0)
                .collect(Collectors.toList());
        PlanetExportDto [] planetExportDtos = new PlanetExportDto[planetsWithoutOriginatedAnomaly.size()];
        for (int index = 0; index < planetsWithoutOriginatedAnomaly.size(); index++) {
            PlanetExportDto planetExportDto = new PlanetExportDto();
            Planet planetToExport = planetsWithoutOriginatedAnomaly.get(index);
            planetExportDto.setName(planetToExport.getName());
            planetExportDtos [index] = planetExportDto;
        }
            this.jsonParser.writeJSON(planetExportDtos, OUTPUT_JSON_FILES_FOLDER_URI + "planets.json");
    }

    private void exportPeopleWhichHaveNotBeenVictims() {
        List<Person> allPersons = this.personService.findAll();
        List<Person> nonVictimPersons = allPersons
                .stream()
                .filter(person -> person.getAnomalies().size() == 0)
                .collect(Collectors.toList());
        PeopleExportDto [] peopleExportDtos = new PeopleExportDto[nonVictimPersons.size()];
        for (int index = 0; index < nonVictimPersons.size(); index++) {
            PeopleExportDto peopleExportDto = new PeopleExportDto();
            Person person = nonVictimPersons.get(index);
            peopleExportDto.setName(person.getName());
            HomePlanetExportDto homePlanetExportDto = new HomePlanetExportDto();
            homePlanetExportDto.setName(person.getHomePlanet().getName());
            peopleExportDto.setHomePlanet(homePlanetExportDto);
            peopleExportDtos[index] = peopleExportDto;
        }

        this.jsonParser.writeJSON(peopleExportDtos, OUTPUT_JSON_FILES_FOLDER_URI + "people.json");
    }

    private void exportAnomalyWhichAffectedTheMostPeopleToJson() {
        List<Anomaly> allAnomalies = this.anomalyService.findAll();
        Anomaly mostAffectingAnomaly = allAnomalies
                .stream()
                .max(Comparator.comparing(anomaly -> anomaly.getVictims().size()))
                .orElse(null);
        AnomalyExportDto anomalyExportDto = new AnomalyExportDto();
        anomalyExportDto.setId(mostAffectingAnomaly.getId());
        anomalyExportDto.setVictimsCount(mostAffectingAnomaly.getVictims().size());
        OriginPlanetExportDto originPlanetExportDto = new OriginPlanetExportDto();
        originPlanetExportDto.setName(mostAffectingAnomaly.getOriginPlanet().getName());
        TeleportPlanetExportDto teleportPlanetExportDto = new TeleportPlanetExportDto();
        teleportPlanetExportDto.setName(mostAffectingAnomaly.getTeleportPlanet().getName());
        anomalyExportDto.setOriginPlanet(originPlanetExportDto);
        anomalyExportDto.setTeleportPlanet(teleportPlanetExportDto);
        AnomalyExportDto [] arrayOfIt = new AnomalyExportDto[1];
        arrayOfIt[0] = anomalyExportDto;
        this.jsonParser.writeJSON(arrayOfIt, OUTPUT_JSON_FILES_FOLDER_URI + "anomaly.json");
    }


    private void exportingDataToXMl() throws JAXBException {
        List<Anomaly> allAnomalies = this.anomalyService.findAll();
        AnomaliesExportXMLDto exportXMLDto = new AnomaliesExportXMLDto();
        List<AnomalyExportXMLDto> anomalyExportDtos = new ArrayList<>();
        for (Anomaly anomaly: allAnomalies) {
            Set<Person> victims = anomaly.getVictims();
            List<VictimExportXMLDto> victimXMLDtos = new ArrayList<>();
            for (Person victim : victims) {
                VictimExportXMLDto victimXMLDto = new VictimExportXMLDto();
                victimXMLDto.setName(victim.getName());
                victimXMLDtos.add(victimXMLDto);
            }

            AnomalyExportXMLDto anomalyXMLDto = new AnomalyExportXMLDto();
            anomalyXMLDto.setId(anomaly.getId());
            anomalyXMLDto.setOriginPlanet(anomaly.getOriginPlanet().getName());
            anomalyXMLDto.setTeleportPlanet(anomaly.getTeleportPlanet().getName());
            anomalyXMLDto.setVictims(victimXMLDtos);
            anomalyExportDtos.add(anomalyXMLDto);
        }

        exportXMLDto.setAnomalyExportDtos(anomalyExportDtos);
        this.xmlParser.writeToXML(exportXMLDto, OUTPUT_XML_FILES_FOLDER_URI + "anomalies.xml");

    }


    private void importingDataFromXML() throws JAXBException, SAXException {
        try {
            AnomaliesImportDto anomalyWithVictimImportDto = this.xmlParser.readFromXml(AnomaliesImportDto.class, INPUT_XML_FILES_FOLDER_URI + "new-anomalies.xml");
            List<AnomalyWithVictimImportDto> anomalies = anomalyWithVictimImportDto.getAnomalies();
            for (AnomalyWithVictimImportDto anomalyWithVictims : anomalies) {
                try {
                    String originPlanetName = anomalyWithVictims.getOriginPlanet();
                    String teleportPlanetName = anomalyWithVictims.getTeleportPlanet();
                    Planet originPlanet = this.planetService.findByName(originPlanetName);
                    Planet teleportPlanet = this.planetService.findByName(teleportPlanetName);
                    List<VictimImportDto> victimDtos = anomalyWithVictims.getVictims();
                    Set<Person> victims = new HashSet<>();
                    for (VictimImportDto victimDto : victimDtos) {
                        String victimName = victimDto.getName();
                        Person victim = this.personService.findByName(victimName);
                        victims.add(victim);
                    }

                    Anomaly anomaly = new Anomaly(originPlanet, teleportPlanet, victims);
                    this.anomalyService.save(anomaly);
                    this.writer.printLine(Messages.SUCCESSFULLY_IMPORTED_DATA_MESSAGE);
                } catch (IllegalArgumentException e) {
                    this.writer.printLine(INVALID_DATA_ERROR);
                }
            }
        }catch (IllegalArgumentException ex) {
            this.writer.printLine(INVALID_DATA_ERROR);
        }
    }
}
