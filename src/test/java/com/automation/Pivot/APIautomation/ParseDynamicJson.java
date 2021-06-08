package com.automation.Pivot.APIautomation;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParseDynamicJson {

	public static void parseObject(JSONObject json, String key) {
		// System.out.println(json.has(key));
		System.out.println(json.get(key));
	}

	public static void getKey(JSONObject json, String key) {
		boolean found = json.has(key);
		Iterator<?> keys;
		String nextKey;

		if (!found) {
			keys = json.keys();
			while (keys.hasNext()) {
				nextKey = (String) keys.next();
				try {
					if (json.get(nextKey) instanceof JSONObject) {
						if (found == false) {
							getKey(json.getJSONObject(nextKey), key);
						}
					} else if (json.get(nextKey) instanceof JSONArray) {
						JSONArray jsonArray = json.getJSONArray(nextKey);
						for (int i = 0; i < jsonArray.length(); i++) {
							String jsonArrayString = jsonArray.get(i).toString();
							JSONObject innerJson = new JSONObject(jsonArrayString);
							if (found == false) {
								getKey(innerJson, key);
							}
						}
					}
				} catch (Exception e) {

				}
			}

		} else {
			parseObject(json, key);
		}

	}

	public static void main(String[] args) {

		String inputJson = "{\r\n"
				+ "  \"InsuranceCompanies\":  {\r\n"
				+ "    \"Time\":\"Feb 2019\",\r\n"
				+ "    \"Top Insurance Companies\":[\r\n"
				+ "      {\r\n"
				+ "        \"No\": \"1\",\r\n"
				+ "        \"Name\": \"Berkshire Hathaway ( BRK.A)\",\r\n"
				+ "        \"Market Capitalization\": \"$308 billion\"\r\n"
				+ "      },\r\n"
				+ "      {\r\n"
				+ "        \"No\": \"2\",\r\n"
				+ "        \"Name\": \"China Life Insurance (LFC)\",\r\n"
				+ "        \"Market Capitalization\": \"$80  billion\"\r\n"
				+ "      },\r\n"
				+ "      {\r\n"
				+ "        \"No\": \"3\",\r\n"
				+ "        \"Name\": \"Allianz (AZSEY) \",\r\n"
				+ "        \"Market Capitalization\": \"$76.8 billion\"\r\n"
				+ "      },\r\n"
				+ "      {\r\n"
				+ "        \"No\": \"4\",\r\n"
				+ "        \"Name\": \"American International Group (AIG)\",\r\n"
				+ "        \"Market Capitalization\": \"$72.3 billion\"\r\n"
				+ "      },\r\n"
				+ "      {\r\n"
				+ "        \"No\": \"5\",\r\n"
				+ "        \"Name\": \"Ping An of China (PNGAY)\",\r\n"
				+ "        \"Market Capitalization\": \"$76.8 billion\"\r\n"
				+ "      },\r\n"
				+ "      {\r\n"
				+ "        \"No\": \"6\",\r\n"
				+ "        \"Name\": \"MetLife (MET)\",\r\n"
				+ "        \"Market Capitalization\": \"$59.4 billion\"\r\n"
				+ "      },\r\n"
				+ "      {\r\n"
				+ "        \"No\": \"7\",\r\n"
				+ "        \"Name\": \"AXA ( AXA)\",\r\n"
				+ "        \"Market Capitalization\": \"$57.8 billion\"\r\n"
				+ "      },\r\n"
				+ "      {\r\n"
				+ "        \"No\": \"8\",\r\n"
				+ "        \"Name\": \"AIA Group Hong Kong (AAIGF)\",\r\n"
				+ "        \"Market Capitalization\": \"$54.4 billion\"\r\n"
				+ "      },\r\n"
				+ "      {\r\n"
				+ "        \"No\": \"9\",\r\n"
				+ "        \"Name\": \"ING Groep (ING)\",\r\n"
				+ "        \"Market Capitalization\": \"$54.4 billion\"\r\n"
				+ "      },\r\n"
				+ "      {\r\n"
				+ "        \"No\": \"10\",\r\n"
				+ "        \"Name\": \"Zurich Insurance (ZURVY)\",\r\n"
				+ "        \"Market Capitalization\": \"$45.4 billion\"\r\n"
				+ "      }\r\n"
				+ "      \r\n"
				+ "    ],\r\n"
				+ "  \"source\": \"investopedia.com\",\r\n"
				+ "  \"url\" :\"https://www.investopedia.com/articles/active-trading/111314/top-10-insurance-companies-metrics.asp\"  \r\n"
				+ "  }\r\n"
				+ "}\r\n"
				+ "";

		JSONObject inputJsonObj = new JSONObject(inputJson);
		getKey(inputJsonObj, "Name");

	}

}
