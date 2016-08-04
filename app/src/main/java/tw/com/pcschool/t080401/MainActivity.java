package tw.com.pcschool.t080401;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputStream is = null;
        is = getResources().openRawResource(R.raw.anim);
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        try {
            while ((length = is.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Log.d("READ", result.toString("UTF-8"));
            String str = result.toString("UTF-8");
            /*
            JSONArray array = new JSONArray(str);
            for (int i=0;i<array.length();i++)
            {
                JSONObject obj = array.getJSONObject(i);
                Log.d("READ", obj.getString("district") + "," + obj.getString("address") + "," + obj.getString("tel"));
            }
*/
            Gson gson = new Gson();
            ArrayList<Animal> mylist = gson.fromJson(str, new TypeToken<ArrayList<Animal>>() {}.getType());

            for (Animal p : mylist)
            {
                Log.d("NET", p.district + "," + p.address + "," + p.tel);
            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
