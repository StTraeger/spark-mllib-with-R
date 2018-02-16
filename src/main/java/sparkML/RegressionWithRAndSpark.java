package sparkML;

import org.apache.spark.SparkContext;
import org.apache.spark.SparkFiles;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

public class RegressionWithRAndSpark {

	public static void main(String[] args) {
		SparkSession session = SparkSession.builder().appName("RegressionWithRAndSpark").config("spark.master", "local")
				.getOrCreate();
		SparkContext sparkContext = session.sparkContext();
		sparkContext.setLogLevel("ERROR");

		JavaSparkContext javaSparkContext = new JavaSparkContext(sparkContext);

		String data = "./data/Einwohnerzahlen_MUC.csv";
		String rScriptRegression = "./scripts/PopulationRegressionMUC.R";
		String rScriptPreprocessing = "./scripts/DataPreprocessing.R";
		String regressionScriptName = "PopulationRegressionMUC.R";
		String preprocessingScriptName = "DataPreprocessing.R";

		// Add R file to SparkContext to be available from all nodes
		sparkContext.addFile(rScriptPreprocessing);
		sparkContext.addFile(rScriptRegression);

		JavaRDD<String> inputData = Dataloader.readCsvAsStringRDD(javaSparkContext, data);

		JavaRDD<String> processedData = inputData.pipe(SparkFiles.get(preprocessingScriptName));
		System.out.println(processedData.collect());

		JavaRDD<String> finalData = processedData.pipe(SparkFiles.get(regressionScriptName));
		System.out.println(finalData.collect());

	}

}
