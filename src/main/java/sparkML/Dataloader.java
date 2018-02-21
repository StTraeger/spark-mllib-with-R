package sparkML;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

import models.PopulationEntry;

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

	public static JavaRDD<PopulationEntry> readCsvAsPopulationEntryRDD(final JavaSparkContext context,
			final String path) {
		if (context != null && path != null && !path.equals("")) {
			JavaRDD<String> data = context.textFile(path);
			// Removing header from JavaRDD
			final String header = data.first();
			JavaRDD<String> dataWithoutHeader = data.filter(new Function<String, Boolean>() {
				public Boolean call(String line) throws Exception {
					return !line.equalsIgnoreCase(header);
				}
			});

			return dataWithoutHeader.map(new Function<String, PopulationEntry>() {
				public PopulationEntry call(String line) throws Exception {
					String[] fields = line.split(",");
					PopulationEntry entry = new PopulationEntry(Integer.valueOf(fields[0]), Integer.valueOf(fields[1]));
					return entry;
				}
			});
		}
		return null;
	}

}
