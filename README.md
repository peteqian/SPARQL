# SPARQL Endpoint

This is a project for Semantic Web to send queries to a fuseki API endpoint containing a dataset.

## Installation

### Fuseki Server

#### Windows
The fuseki server must be first activated by running the ```fuseki-server.bat``` which will start up the server on ```localhost:3030```. 

Navigate to the endpoint on a browser (Firefox, Chrome, Brave, etc.) and you'll arrive to the welcome page.

From there, create a dataset and give it any name you want.

Upload the file ```dataset\Values-Map-Ontology-RDF-XML.owl``` to the dataset.

### SPARQL Project

Next import the folder ```spaqrlendpointproject``` as a maven project and import from external sources. It is important that the folder you're working off is from the ```sparqlendpointproject``` folder as this is where the system will use this path for finding the queries located in ```\resources``` folder.

Once you have the project installed, ensure all dependencies are downloaded and indexed.

## Usage

1. Queries are located in ```resources\queries``` folder.
2. Change ```fileOne``` on line:65 to any of the query file names in ```resources\queries```.
3. Run ```QueryExample.java``` file.


### Example

![image](https://user-images.githubusercontent.com/60642461/139572189-0ab76f73-6a76-4d9a-afd8-f30c4adc6f5c.png)
