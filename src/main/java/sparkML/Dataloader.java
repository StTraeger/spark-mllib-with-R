package sparkML;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

public class Dataloader {

	public static JavaRDD<String> readCsvAsStringRDD(final JavaSparkContext context, final String path) {
		if (context != null && path != null && !path.equals(""))
			return context.textFile(path);
		return null;
	}

	public static Dataset<Row> readCsvAsRowDataset(final SQLContext sqlContext, final String path) {
		if (sqlContext != null && path != null && !path.equals(""))
			return sqlContext.read().format("csv").option("header", "true").option("inferSchema", "true").load(path);
		return null;
	}

}
