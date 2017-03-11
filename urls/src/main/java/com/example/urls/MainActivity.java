package com.example.urls;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Children children1;
    ListView listv;
    List<Children> list;
    TextView text;
    JsonsRootBean rootBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        listv= (ListView) findViewById(R.id.listv);
//        text= (TextView) findViewById(R.id.text222);
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    URL url = new URL("https://mock.eolinker.com/success/LISNRPQd9eVEpj1qFQlh5h5EJDKAVQJJ");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    //设置连接方式
                    urlConnection.setRequestMethod("GET");
                    //设置连接时间(网络请求耗时操作给的时间太短)
                    urlConnection.setConnectTimeout(3000);


                    list = new ArrayList<Children>();
                    if (urlConnection.getResponseCode() == 200) {


                        StringBuffer sb = new StringBuffer();
                        String read;
                        InputStream is = urlConnection.getInputStream();
                        InputStreamReader isr = new InputStreamReader(is);
                        BufferedReader br = new BufferedReader(isr);
                        while ((read = br.readLine()) != null) {
                            sb.append(read);
                        }

                        Log.i("zzz", sb.toString());
                        String json = sb.toString();

                        Gson gson = new Gson();

                        rootBean = gson.fromJson(json, JsonsRootBean.class);
                        List<Data> data = rootBean.getData();
                        for (int i = 0; i < data.size(); i++) {
                            int id = data.get(i).getId();
                            String title = data.get(i).getTitle();
                            int type = data.get(i).getType();
                            if (data.get(i).getChildren() != null) {

                                List<Children> children = data.get(i).getChildren();
                                for (int j = 0; j < children.size(); j++) {
                                    children1 = new Children();
                                    int cid = children.get(j).getId();
                                    String ctitle = children.get(j).getTitle();
                                    int cype = children.get(j).getType();
                                    String curl = children.get(j).getUrl();

                                    children1.setId(cid);
                                    children1.setTitle(ctitle);
                                    children1.setType(cype);
                                    children1.setUrl(curl);

                                    list.add(children1);
                                    Log.e("qqqqqqqqqqqqqqq",children1.toString());
                                }
                            }

                        }

                    }


                //   text.setText(rootBean.toString());
                  listv.setAdapter(new MyListViewAdapter());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }




    class MyListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewholder;
            if (convertView == null) {
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_adapter, null);
                viewholder = new ViewHolder();
                viewholder._id = (TextView) convertView.findViewById(R.id._id);
                viewholder._title= (TextView) convertView.findViewById(R.id._title);
                viewholder._type= (TextView) convertView.findViewById(R.id._type);
                viewholder._url= (TextView) convertView.findViewById(R.id._url);
                convertView.setTag(viewholder);
            }else{
                viewholder= (ViewHolder) convertView.getTag();
            }
            viewholder._url.setText(list.get(position).getUrl());
            viewholder._type.setText(list.get(position).getType()+"");
            viewholder._title.setText(list.get(position).getTitle());
            viewholder._id.setText(list.get(position).getId()+"");
            return convertView;
        }
    }

    class ViewHolder {
        TextView _id, _title, _type, _url;

    }
}
