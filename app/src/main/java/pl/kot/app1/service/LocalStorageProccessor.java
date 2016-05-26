package pl.kot.app1.service;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import pl.kot.app1.model.classes.ZapisaneDaneAplikacji;

/**
 * Created by Damian on 26/05/2016.
 */
public class LocalStorageProccessor {
    public static final String LOCAL_STORAGE_FILE_NAME= "appDataHistory";

    Context context;
    public LocalStorageProccessor(Context context) {
        this.context = context;
    }

    public void zapiszDoLocalStoragePlik(ZapisaneDaneAplikacji zapisaneDaneAplikacji) {
    Log.d("INSIDE METHOD", "zapiszDoLOcalStoragePlik.");

        try {
            FileOutputStream outputStream =
                    context.openFileOutput(LOCAL_STORAGE_FILE_NAME, Context.MODE_PRIVATE);

            Gson gson = new Gson();
            String json = gson.toJson(zapisaneDaneAplikacji);
            System.out.println("GSON: " + json);
            outputStream.write(json.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ZapisaneDaneAplikacji pobierzZapisaneDaneAplikacji() {
        Log.d("INSIDE METHOD", "pobierzZapisaneDaneAplikacji.");

        ZapisaneDaneAplikacji zapisaneDaneAplikacji;
        try {
            FileInputStream inputStream = context.openFileInput(LOCAL_STORAGE_FILE_NAME);

            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            r.close();
            inputStream.close();

            Log.d("PLIK ", "ZAWARTOSC HISTORII APLIKACJI: " + total);

            Gson gson = new Gson();
            zapisaneDaneAplikacji = gson.fromJson(total.toString(), ZapisaneDaneAplikacji.class);

            return zapisaneDaneAplikacji;
        } catch (FileNotFoundException e) {
            Log.e("FILE_NOT_FOUND", "NIE ISTNIEJE PLIK HISTORII APLIKACJI.");
        } catch (Exception e) {
            Log.e("PLIK HISTORII APLIKACJI", "Nie istnieje plik historii aplikacji");
            e.printStackTrace();
        }

        return null;
    }

}
