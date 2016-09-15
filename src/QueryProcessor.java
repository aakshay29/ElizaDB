import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class QueryProcessor {
	public Map<Integer, String> hedgeSet = new HashMap<Integer, String>();
	public Map<Integer, String> qualifierSet = new HashMap<Integer, String>();
	public Map<String, String> replacementMap = new HashMap<String, String>();
	Random rnd = new Random();

	public String getAnswer(String query, String name) {
		String answer = null;
		int randomInt = 1 + rnd.nextInt(2);
		String delim = " ";
		String[] parts = query.split(delim);

		if (randomInt == 1) {
			int randomIntHedge = 1 + rnd.nextInt(8);
			answer = hedgeSet.get(randomIntHedge);
		} 
		else if (randomInt == 2) {
			String temp = "";
			for (int i = 0; i < parts.length; i++) {
				if (replacementMap.containsKey(parts[i])) {
					parts[i] = replacementMap.get(parts[i]);
				}
				temp += parts[i] + " ";
			}
			int randomIntQualifier = 1 + rnd.nextInt(4);
			answer = qualifierSet.get(randomIntQualifier) + temp;
		}
		for (int j = 0; j < parts.length; j++) {
			if (parts[j].equalsIgnoreCase("sad")) {
				answer = "Why are you sad " + name + "?";
			}
			if (parts[j].equalsIgnoreCase("gloomy")) {
				answer = "Why are you gloomy " + name + "?";
			}
			if (parts[j].equalsIgnoreCase("bad")) {
				answer = "You should never feel bad about anything.";
			}
			if (parts[j].equalsIgnoreCase("happy")) {
				answer = "Happiness is the key to success.";
			}
			if (parts[j].equalsIgnoreCase("life")) {
				answer = "I know you have a great potential in life.";
			}
			if (parts[j].equalsIgnoreCase("run")) {
				answer = "No body can run away from important things in life.";
			}
			if (parts[j].equalsIgnoreCase("yeah") || parts[j].equalsIgnoreCase("yes") || parts[j].equalsIgnoreCase("yup")) {
				answer = "Why do you think so?";
			}
		}
		return answer;
	}

	public void populateSets(String name) {
		hedgeSet.put(1, "Please tell me more.");
		hedgeSet.put(2, "Many of my patients tell me the same thing.");
		hedgeSet.put(3, "That is a very common complaint these days I must say.");
		hedgeSet.put(4, "When did this start?");
		hedgeSet.put(5, "Hmm...Go on");
		hedgeSet.put(6, "Really?");
		hedgeSet.put(7, "I see whats going on here. Give me more details.");
		hedgeSet.put(8, "How often do you feel this way?");

		qualifierSet.put(1, name + ", why do you say ");
		qualifierSet.put(2, "You seem to think that ");
		qualifierSet.put(3, "Well...you think ");
		qualifierSet.put(4, "So you are saying that ");

		replacementMap.put("i", "you");
		replacementMap.put("me", "you");
		replacementMap.put("my", "your");
		replacementMap.put("am", "are");
	}
}
