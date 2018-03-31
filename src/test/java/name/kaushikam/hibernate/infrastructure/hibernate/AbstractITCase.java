package name.kaushikam.hibernate.infrastructure.hibernate;

import lombok.Getter;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.DefaultTable;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.operation.TruncateTableOperation;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

@Getter
public abstract class AbstractITCase {

    private IDatabaseTester databaseTester;

    private String schema;

    protected void configure (IDatabaseTester databaseTester, String schema) {
        this.databaseTester = databaseTester;
        this.schema = schema.toUpperCase();
    }

    protected IDataSet getDataSet(String datasetFileName) throws Exception {
        File file = new File(getClass().getClassLoader()
                .getResource(datasetFileName).getFile());
        return new FlatXmlDataSetBuilder().setColumnSensing(true).build(file);
    }

    protected IDataSet getReplacementDataSet(IDataSet dataSet) throws Exception {
        ReplacementDataSet replacementDataSet = new ReplacementDataSet(dataSet);
        replacementDataSet.addReplacementObject("[NULL]", null);
        return replacementDataSet;
    }

    protected void setup(String filename) throws Exception {
        getDatabaseTester().setDataSet(getReplacementDataSet(getDataSet(filename)));
        getDatabaseTester().setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        getDatabaseTester().onSetup();
    }

    protected String getFile(String fileName) {
        StringBuilder result = new StringBuilder("");

        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    protected void truncateTable(String tableName) throws Exception {
        TruncateTableOperation.TRUNCATE_TABLE.execute(
                getDatabaseTester().getConnection(),
                new DefaultDataSet(new DefaultTable(getSchema() + "." + tableName))
        );
    }

    protected void deleteTable(String tableName) throws Exception {
        TruncateTableOperation.DELETE.execute(
                getDatabaseTester().getConnection(),
                new DefaultDataSet(new DefaultTable(getSchema() + "." + tableName))
        );
    }

    protected void nativeTruncateTable(String tableName) throws Exception {
        getDatabaseTester().getConnection().getConnection()
                .prepareStatement("TRUNCATE TABLE " + getSchema() + "." + tableName).execute();
    }

    protected void nativeDeleteTable(String tableName) throws Exception {
        getDatabaseTester().getConnection().getConnection()
                .prepareStatement("DELETE FROM " + getSchema() + "." + tableName).execute();
    }
}
