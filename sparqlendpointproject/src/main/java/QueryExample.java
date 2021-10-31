import org.apache.jena.query.*;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

/**
 * Created by Isai B. Cicourel
 */
public class QueryExample {


    /**
     * Query an Endpoint using the given SPARQl query
     * @param szQuery
     * @param szEndpoint
     * @throws Exception
     */
    public void queryEndpoint(String szQuery, String szEndpoint)
            throws Exception
    {
        // Create a Query with the given String
        Query query = QueryFactory.create(szQuery);

        // Create the Execution Factory using the given Endpoint
        QueryExecution qexec = QueryExecutionFactory.sparqlService(
                szEndpoint, query);

        // Set Timeout
        ((QueryEngineHTTP)qexec).addParam("timeout", "10000");


        // Execute Query
        int iCount = 0;
        ResultSet rs = qexec.execSelect();
        while (rs.hasNext()) {
            // Get Result
            QuerySolution qs = rs.next();

            // Get Variable Names
            Iterator<String> itVars = qs.varNames();

            // Count
            iCount++;
            System.out.println("Result " + iCount + ": ");

            // Display Result
            while (itVars.hasNext()) {
                String szVar = itVars.next().toString();
                String szVal = qs.get(szVar).toString();

                System.out.println("[" + szVar + "]: " + szVal);
            }
        }
    } // End of Method: queryEndpoint()


    public static void main(String[] args) throws IOException {

        // File
        String fileOne = "selectSubClass.sparql";

        // Path Query
        String PATH_QUERY = System.getProperty("user.dir")
                + File.separator + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "queries" + File.separator + fileOne;

        // SPARQL Query
        // String szQuery = "select * where {?Subject ?Predicate ?Object} LIMIT 10";
        String szQuery = QueryFactory.read(PATH_QUERY).toString();

        // Arguments
        if (args != null && args.length == 1) {
            szQuery = new String(
                    Files.readAllBytes(Paths.get(args[0])),
                    Charset.defaultCharset());
        }

        // Endpoint
        String szEndpoint = "http://localhost:3030//values-map-dataset/query";

        // Query DBPedia
        try {
            QueryExample q = new QueryExample();
            q.queryEndpoint(szQuery, szEndpoint);
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
    }
}