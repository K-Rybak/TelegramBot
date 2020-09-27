import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Rate {
    public String getResponse() throws IOException {

        URL url = new URL("http://localhost:8080/rates/all");

        Scanner scanner = new Scanner((InputStream) url.getContent());
        String result = "";

        while (scanner.hasNext()) {
            result += scanner.nextLine();
        }

        String[] nameRate;
        String[] valueRate;
        String rateResult = "";

        JSONArray jsonArray = new JSONArray(result);

        nameRate = new String[jsonArray.length()];
        valueRate = new String[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            nameRate[i] = jsonObject.getString("nameRate");
            valueRate[i] = jsonObject.getString("valueRate");
        }

        for (int i = 0; i < nameRate.length; i++) {
            rateResult += nameRate[i] + "\n"
                    + valueRate[i] + "\n";
        }

        return "Текущий курс:" + "\n"
                +rateResult;
    }
}
