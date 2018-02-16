package sparkML;

import org.apache.spark.SparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;

public class RegressionWithDatasetAndR {

	public static void main(String[] args) {
		SparkSession session = SparkSession.builder().appName("RegressionWithDatasetAndR")
				.config("spark.master", "local").getOrCreate();
		SparkContext sparkContext = session.sparkContext();
		sparkContext.setLogLevel("ERROR");
		SQLContext sqlContext = session.sqlContext();

		String data = "./data/Einwohnerzahlen_MUC.csv";
		String rScriptRegression = "./scripts/PopulationRegressionMUC.R";
		String rScriptPreprocessing = "./scripts/DataPreprocessing.R";
		String regressionScriptName = "PopulationRegressionMUC.R";
		String preprocessingScriptName = "DataPreprocessing.R";

		Dataset<Row> inputData = Dataloader.readCsvAsRowDataset(sqlContext, data);
		inputData.show();

	}

}
