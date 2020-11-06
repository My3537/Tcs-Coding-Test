package com.currency.convertor.filereader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.currency.convertor.types.Build;
import com.currency.convertor.types.DataFile;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
@EnableScheduling
public class CurrencyConvertorFileReader {

	private static final Logger logger = LoggerFactory.getLogger(CurrencyConvertorFileReader.class);

	Set<String> buildIds = new HashSet<String>();
	Map<String, Float> currencyMap = new HashMap<String, Float>();

	@Scheduled(cron = "0 */1 * ? * *") // runs every 1 min
	private void readBuildFile() {
		try (Reader buildFileReader = new FileReader("tmp/exchange/buildID.txt")) {
			Gson gson = new Gson();
			Build build = gson.fromJson(buildFileReader, Build.class);
			String buildId = build.getBuildID();
			logger.info("Build ID: " + buildId);
			if (!buildIds.isEmpty() && buildIds.contains(buildId)) {
				logger.info("Ignoring this build file as the build id is already exists");
			} else {
				String dataFileName = build.getFileName();
				logger.info("FileName: " + dataFileName);
				Reader dataFileReader = new FileReader("tmp/exchange/" + dataFileName);
				Gson dataFileGson = new GsonBuilder().setFieldNamingPolicy(
						FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
				DataFile dataFile = dataFileGson.fromJson(dataFileReader, DataFile.class);
				Float cadUSD = dataFile.getCadUsd();
				currencyMap.put("cad_usd", cadUSD);
				currencyMap.put("fr_usd", dataFile.getFrUsd());

			}

			logger.info("cad_usd Conversion value: " + currencyMap.get("cad_usd"));
			logger.info("fr_usd Conversion Value: " + currencyMap.get("fr_usd"));

		} catch (FileNotFoundException e) {
			logger.error("FileNotFound Exception occured: " + e);
		} catch (IOException e) {
			logger.error("IO Exception occured: " + e);
		} catch (Exception e) {
			logger.error("Exception ocucred " + e);
		}
	}

}
