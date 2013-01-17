package com.bletenkov.PostTrackerLite;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.*;
import android.widget.ListView;
import android.widget.TabHost;
import java.util.ArrayList;

public class Main extends Activity {

    SQLiteWorker sqliteBase;

    private ArrayList<FolderInfo> mainFolders = new ArrayList<FolderInfo>();
    private ArrayList<TrackNumberInfo> mainTracks = new ArrayList<TrackNumberInfo>();
    private ArrayList<TrackNumberInfo> archiveTracks = new ArrayList<TrackNumberInfo>();

    private static String TAG = "MyApp";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        sqliteBase = new SQLiteWorker(this);

        SQLiteDatabase dbase = sqliteBase.getWritableDatabase();
        Cursor cur = dbase.query("folders", null, null, null, null, null, null);

        TabHost _host = (TabHost)findViewById(R.id.tabHost);
        _host.setup();
        if(cur.moveToFirst()){
            do{
                TabHost.TabSpec _spec;
                String id = cur.getString(cur.getColumnIndex("id"));
                _spec = _host.newTabSpec(id);
                String name = cur.getString(cur.getColumnIndex("name"));
                Drawable icon;
                boolean istrack = id.equalsIgnoreCase("mytrc");
                if(istrack){
                    //track
                    icon = getResources().getDrawable(R.drawable.mov);
                    _spec.setIndicator(name, icon);
                    _spec.setContent(R.id.main_tab_main);
                }else{
                    //archive
                    icon = getResources().getDrawable(R.drawable.arc);
                    _spec.setIndicator(name, icon);
                    _spec.setContent(R.id.main_tab_archive);
                }
                _host.addTab(_spec);

                mainFolders.add(new FolderInfo(id, name, false));

            }while(cur.moveToNext());
        }
        cur.close();

        //вкладка статистика
        TabHost.TabSpec _spec = _host.newTabSpec("stat");
        _spec.setIndicator("Statistic", getResources().getDrawable(R.drawable.stat));
        _spec.setContent(R.id.main_tab_stat);
        _host.addTab(_spec);

        _host.setCurrentTabByTag("mytrc");
        _host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(tabId.equalsIgnoreCase("mytrc")){
                    ListView mainList = (ListView)findViewById(R.id.main_listview_track);
                    RecordListItemAdapter adapter = new RecordListItemAdapter(getApplicationContext(), R.layout.record, mainTracks);
                    mainList.setAdapter(adapter);
                }else if (tabId.equalsIgnoreCase("archv")){
                    ListView mainList = (ListView)findViewById(R.id.main_listview_archive);
                    RecordListItemAdapter adapter = new RecordListItemAdapter(getApplicationContext(), R.layout.record, archiveTracks);
                    mainList.setAdapter(adapter);
                }
            }
        });

        //check tracks
        Cursor mainTr = dbase.query("tracknumbers",
                                    null,
                                    "folder=?",
                                    new String[]{"mytrc"},
                                    null,
                                    null,
                                    null,
                                    null);
        if(mainTr.moveToFirst()){
            do{
                int id = mainTr.getInt(mainTr.getColumnIndex("id"));
                String track = mainTr.getString(mainTr.getColumnIndex("tracknumber"));
                long lastcheck = mainTr.getLong(mainTr.getColumnIndex("lastcheck"));
                long dateadd = mainTr.getInt(mainTr.getColumnIndex("dateadd"));
                String comment = mainTr.getString(mainTr.getColumnIndex("comment"));
                String incountry = mainTr.getString(mainTr.getColumnIndex("incountry"));
                String outcountry = mainTr.getString(mainTr.getColumnIndex("outcountry"));

                mainTracks.add(new TrackNumberInfo(id, track, incountry, outcountry, comment, dateadd, lastcheck));
            }while (mainTr.moveToNext());
        }
        mainTr.close();

        //check archive tracks
        Cursor arcTr = dbase.query("tracknumbers",
                                    null,
                                    "folder=?",
                                    new String[]{"archv"},
                                    null,
                                    null,
                                    null,
                                    null);
        if(mainTr.moveToFirst()){
            do{
                int id = arcTr.getInt(mainTr.getColumnIndex("id"));
                String track = arcTr.getString(mainTr.getColumnIndex("tracknumber"));
                long lastcheck = arcTr.getLong(mainTr.getColumnIndex("lastcheck"));
                long dateadd = arcTr.getInt(mainTr.getColumnIndex("dateadd"));
                String comment = arcTr.getString(mainTr.getColumnIndex("comment"));
                String incountry = arcTr.getString(mainTr.getColumnIndex("incountry"));
                String outcountry = arcTr.getString(mainTr.getColumnIndex("outcountry"));

                archiveTracks.add(new TrackNumberInfo(id, track, incountry, outcountry, comment, dateadd, lastcheck));
            }while (mainTr.moveToNext());
        }
        arcTr.close();
        sqliteBase.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_add:
                break;
            case R.id.menu_delete:
                break;
            case R.id.menu_about:
                startActivity(new Intent(this, About.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
