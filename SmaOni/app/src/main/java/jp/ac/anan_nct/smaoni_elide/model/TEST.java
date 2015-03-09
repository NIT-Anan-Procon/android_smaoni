package jp.ac.anan_nct.smaoni_elide.model;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import jp.ac.anan_nct.smaoni_elide.activity.HomeActivity;

/**
 * Created by skriulle on 2015/03/08.
 */
public class TEST extends Activity{

    private static final String TAG = HomeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 今回は必要ないのでレイアウトはセットしません

        AssetManager assetManager = getResources().getAssets();
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;

        try {
            // 事前に用意しておいた、山手線の駅情報を読み込みます
            inputStream = assetManager.open("yamanote_line.json");
            bufferedReader =
                    new BufferedReader(new InputStreamReader(inputStream));
            String str = bufferedReader.readLine();

            // JSONObject に変換します
            JSONObject json = new JSONObject(str);

            // JSONObject を文字列に変換してログ出力します
            Log.d(TAG, json.toString());

            inputStream.close();
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
