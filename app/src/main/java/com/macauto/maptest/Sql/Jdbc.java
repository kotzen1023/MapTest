package com.macauto.maptest.Sql;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.mysql.jdbc.exceptions.*;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc {
    private static final String TAG = Jdbc.class.getName();
    private final static String URL = "jdbc:mysql://35.185.153.232/tennis_score_board_db";
    public final static String USERNAME = "tennis_user";
    public final static String PASSWORD = "rk19791023";
    public final static String DRIVER = "com.mysql.jdbc.Driver";

    private static Statement stat = null;
    private static ResultSet rs = null;
    private static Connection con = null;
    private static  PreparedStatement pst = null;

    private static String insertdbSQL = "insert into court(name, longitude ,latitude, type, court_num, maintenance, rate, night_play, charge) " +
            "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static String querydbSQL = "select * from court ";

    public Jdbc() {
        Log.d(TAG, "Jdbc create");

        /*new Thread() {
            public void run() {
                Connect();
            }
        };

        sqlTask conTask;
        conTask = new sqlTask();
        conTask.execute(10);*/


    }

    private static boolean Connect() {

        Log.d(TAG, "=== Connect start ===");
        boolean ret = false;

        if (con == null) {

            try {
                Class.forName(DRIVER);
                //註冊driver
                con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                if (con != null) {
                    Log.d(TAG, "con = "+con.getClass().getName());
                } else {
                    Log.e(TAG, "con = null");
                }
            }
            catch(ClassNotFoundException e)
            {
                System.out.println("DriverClassNotFound :"+e.toString());
            }//有可能會產生sqlexception
            catch(SQLException x) {
                System.out.println("Exception :"+x.toString());
            }


        } else {
            Log.e(TAG, "Already connected!");
            ret = true;
        }

        Log.d(TAG, "=== Connect end ===");

        return ret;
    }

    private static void Close()
    {
        try
        {
            if(rs!=null)
            {
                rs.close();
                rs = null;
            }
            if(stat!=null)
            {
                stat.close();
                stat = null;
            }
            if(pst!=null)
            {
                pst.close();
                pst = null;
            }
        }
        catch(SQLException e)
        {
            System.out.println("Close Exception :" + e.toString());
        }
    }

    public static void queryTable() {

        new Thread() {
            public void run() {
                Log.d(TAG, "=== queryTable start ===");
                //boolean ret = false;
                if (con == null) {
                    Log.e(TAG, "Connection = null, we must connect first...");
                    Connect();
                } else {
                    Log.e(TAG, "Connection = " + con.getClass().getName());
                }

                if (con != null) {
                    try
                    {
                        stat = con.createStatement();
                        rs = stat.executeQuery(querydbSQL);
                        Log.d(TAG, "=== Data Read ===");
                        //name, longitude ,latitude, type, court_num, maintenance, rate, night_play, charge
                        while(rs.next())
                        {
                            Log.d(TAG, ""+rs.getString("name")+", "+rs.getDouble("longitude")+", "+rs.getDouble("latitude")+", "+rs.getInt("type")+", "+
                            rs.getInt("court_num")+", "+rs.getInt("maintenance")+", "+rs.getInt("rate")+", "+rs.getInt("night_play")+", "+rs.getString("charge"));

                        }
                        Log.d(TAG, "=== Data Read ===");
                    }
                    catch(SQLException e)
                    {
                        System.out.println("DropDB Exception :" + e.toString());
                    }
                    finally
                    {
                        Close();
                    }
                }

                Log.d(TAG, "=== queryTable end ===");
            }
        }.start();

        sqlTask conTask;
        conTask = new sqlTask();
        conTask.execute(10);


    }


    public static void insertTable(final String name, final String longitude, final String latitude, final String type, final String court_num,
                                   final String maintenance, final String rate, final String night_play, final String charge)
    {
        new Thread() {
            public void run() {
                Log.d(TAG, "=== insertTable start ===");
                //boolean ret = false;
                if (con == null) {
                    Log.e(TAG, "Connection = null, we must connect first...");
                    Connect();
                } else {
                    Log.e(TAG, "Connection = "+con.getClass().getName());
                }

                if (con != null) {

                    try {
                        pst = con.prepareStatement(insertdbSQL);

                        pst.setString(1, name);
                        pst.setString(2, longitude);
                        pst.setString(3, latitude);
                        pst.setString(4, type);
                        pst.setString(5, court_num);
                        pst.setString(6, maintenance);
                        pst.setString(7, rate);
                        pst.setString(8, night_play);
                        pst.setString(9, charge);
                        pst.executeUpdate();

                    } catch (SQLException e) {
                        System.out.println("InsertDB Exception :" + e.toString());
                    } finally {
                        Close();
                    }
                }
                Log.d(TAG, "=== insertTable end ===");
            }
        }.start();

        sqlTask conTask;
        conTask = new sqlTask();
        conTask.execute(10);


    }



    private static class sqlTask extends AsyncTask<Integer, Integer, String>
    {
        // <傳入參數, 處理中更新介面參數, 處理後傳出參數>
        //int nowCount;
        @Override
        protected String doInBackground(Integer... countTo) {

            // 再背景中處理的耗時工作
            /*try {
                while(Data.pass_count<selected_names.size()){

                    //nowCount = i + 1;
                    publishProgress(Data.pass_count+1);
                    Thread.sleep(200);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "10";*/
            /*while(decrypting) {
                try {

                    long percent = 0;
                    if (Data.current_file_size > 0)
                        percent = (Data.complete_file_size * 100)/Data.current_file_size;

                    publishProgress((int)percent);
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }*/

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            /*loadDialog = new ProgressDialog(PhotoList.this);
            loadDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            loadDialog.setTitle(R.string.photolist_decrypting_title);
            loadDialog.setProgress(0);
            loadDialog.setMax(100);
            loadDialog.setIndeterminate(false);
            loadDialog.setCancelable(false);

            loadDialog.show();*/
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            super.onProgressUpdate(values);
            // 背景工作處理"中"更新的事
            /*long percent = 0;
            if (Data.current_file_size > 0)
                percent = (Data.complete_file_size * 100)/Data.current_file_size;

            decryptDialog.setMessage(getResources().getString(R.string.photolist_decrypting_files) + "(" + values[0] + "/" + selected_names.size() + ") " + percent + "%\n" + selected_names.get(values[0] - 1));
            */
            /*if (Data.OnDecompressing) {
                loadDialog.setTitle(getResources().getString(R.string.decompressing_files_title) + " " + Data.CompressingFileName);
                loadDialog.setProgress(values[0]);
            } else if (Data.OnDecrypting) {
                loadDialog.setTitle(getResources().getString(R.string.decrypting_files_title) + " " + Data.EnryptingOrDecryptingFileName);
                loadDialog.setProgress(values[0]);
            } else {
                loadDialog.setMessage(getResources().getString(R.string.decrypting_files_title));
            }*/
        }

        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);

            //loadDialog.dismiss();
            /*btnDecrypt.setVisibility(View.INVISIBLE);
            btnShare.setVisibility(View.INVISIBLE);
            btnDelete.setVisibility(View.INVISIBLE);
            selected_count = 0;*/
        }

        @Override
        protected void onCancelled() {

            super.onCancelled();
        }
    }
}
