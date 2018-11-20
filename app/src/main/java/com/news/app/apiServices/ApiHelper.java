package com.news.app.apiServices;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApiHelper extends AsyncTask<String, String, JSONObject> {

    WebServiceConnector connector = new WebServiceConnector();
    ProgressDialog dialog = null;
    String type;
    Context context;
    String webserviceType;
    String categoryId;
    String answer, question, mainUrl;

    public ApiHelper(Context context, String webserviceType, String mainUrl,
                     TaskDelegate delegate) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.delegate = delegate;
        this.webserviceType = webserviceType;
        this.mainUrl = mainUrl;
        System.out.println("mainUrl===" + mainUrl);
    }

    private TaskDelegate delegate;



    // here is the task protocol to can delegate on other object
    public interface TaskDelegate {

        void onTaskFisnishGettingData(JSONObject result);
    }

    @Override
    protected void onPreExecute() {
        if ((!webserviceType.equals("hitcounter")) && (!webserviceType.equals("hitCategorycounter"))) {
            dialog = ProgressDialog.show(context, "", "Please wait...");
        }
    }

    protected JSONObject doInBackground(String... args) {
        JSONObject jsonObject = null;
        try {
            if (webserviceType.equals("forecastResponse")) {
                jsonObject = connector
                        .makeHttpRequest(
                                mainUrl,
                                "GET", null);
                System.out.println("json==" + jsonObject);
                return jsonObject;

            }else if (webserviceType.equals("newsResponse")) {
                jsonObject = connector
                        .makeHttpRequest(
                                mainUrl,
                                "GET", null);
                System.out.println("json==" + jsonObject);
                return jsonObject;

            }else if (webserviceType.equals("brand")) {
                jsonObject = connector
                        .makeHttpRequest(
                                mainUrl,
                                "GET", null);
                System.out.println("json==" + jsonObject);
                return jsonObject;

            } else if (webserviceType.equals("services")) {
                jsonObject = connector
                        .makeHttpRequest(
                                mainUrl,
                                "GET", null);
                System.out.println("json==" + jsonObject);
                return jsonObject;

            } else if (webserviceType.equals("mechanic_list")) {
                jsonObject = connector
                        .makeHttpRequest(
                                mainUrl,
                                "GET", null);
                System.out.println("json==" + jsonObject);
                return jsonObject;

            } else if (webserviceType.equals("price_list")) {

                jsonObject = connector
                        .makeHttpRequest(mainUrl,

                                "GET", null);
                System.out.println("json==" + jsonObject);
                return jsonObject;

            } else if (webserviceType.equals("register")) {

                jsonObject = connector
                        .makeHttpRequest(
                                mainUrl,
                                "GET", null);
                System.out.println("json==" + jsonObject);
                return jsonObject;

            } else if (webserviceType.equals("generateOtp")) {

                jsonObject = connector
                        .makeHttpRequest(
                                mainUrl,
                                "GET", null);
                System.out.println("json==" + jsonObject);
                return jsonObject;

            } else if (webserviceType.equals("verifyOtp")) {

                jsonObject = connector
                        .makeHttpRequest(
                                mainUrl,
                                "GET", null);
                System.out.println("json==" + jsonObject);
                return jsonObject;


            }else if (webserviceType.equals("all_address_list")) {

                jsonObject = connector
                        .makeHttpRequest(mainUrl,

                                "GET", null);
                System.out.println("json==" + jsonObject);
                return jsonObject;

            } else if (webserviceType.equals("add_address")) {

                jsonObject = connector
                        .makeHttpRequest(mainUrl,

                                "GET", null);
                System.out.println("json==" + jsonObject);
                return jsonObject;

            }else if (webserviceType.equals("edit_address")) {

                jsonObject = connector
                        .makeHttpRequest(mainUrl,

                                "GET", null);
                System.out.println("json==" + jsonObject);
                return jsonObject;

            } else if (webserviceType.equals("bookOrder")) {

                jsonObject = connector
                        .makeHttpRequest(mainUrl,

                                "GET", null);
                System.out.println("json==" + jsonObject);
                return jsonObject;

            }else if (webserviceType.equals("allOrders")) {

                jsonObject = connector
                        .makeHttpRequest(mainUrl,

                                "GET", null);
                System.out.println("json==" + jsonObject);
                return jsonObject;

            }else if (webserviceType.equals("check_refer_code")) {

                jsonObject = connector
                        .makeHttpRequest(mainUrl,

                                "GET", null);
                System.out.println("json==" + jsonObject);
                return jsonObject;

            }else if (webserviceType.equals("apply_refer_code")) {

                jsonObject = connector
                        .makeHttpRequest(mainUrl,

                                "GET", null);
                System.out.println("json==" + jsonObject);
                return jsonObject;

            } else if (webserviceType.equals("get_refer_code")) {

                jsonObject = connector
                        .makeHttpRequest(mainUrl,

                                "GET", null);
                System.out.println("json==" + jsonObject);
                return jsonObject;

            } else if (webserviceType.equals("all_cities")) {

                jsonObject = connector
                        .makeHttpRequest(mainUrl,

                                "GET", null);
                System.out.println("json==" + jsonObject);
                return jsonObject;

            }else if (webserviceType.equals("allUsers")) {

                jsonObject = connector
                        .makeHttpRequest(mainUrl,

                                "GET", null);
                System.out.println("json==" + jsonObject);
                return jsonObject;

            } else if (webserviceType.equals("registerFcm")) {

                jsonObject = connector
                        .makeHttpRequest(mainUrl,

                                "GET", null);
                System.out.println("json==" + jsonObject);
                return jsonObject;

            } else if (webserviceType.equals("wallet")) {

                jsonObject = connector
                        .makeHttpRequest(mainUrl,

                                "GET", null);
                System.out.println("json==" + jsonObject);
                return jsonObject;

            } else if (webserviceType.equals("userBikes")) {

                jsonObject = connector
                        .makeHttpRequest(mainUrl,

                                "GET", null);
                System.out.println("json==" + jsonObject);
                return jsonObject;

            } else if (webserviceType.equals("feedBack")) {

                jsonObject = connector
                        .makeHttpRequest(mainUrl,

                                "GET", null);

                System.out.println("json==" + jsonObject);
                return jsonObject;
            } else if (webserviceType.equals("applyPromocode")) {

                jsonObject = connector
                        .makeHttpRequest(mainUrl,

                                "GET", null);
                System.out.println("json==" + jsonObject);
                return jsonObject;

            } else if (webserviceType.equals("cancelOrder")) {

                jsonObject = connector
                        .makeHttpRequest(mainUrl,

                                "GET", null);
                System.out.println("json==" + jsonObject);
                return jsonObject;

            }  else if (webserviceType.equals("city")) {

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                //	params.add(new BasicNameValuePair("name", name));
                //params.add(new BasicNameValuePair("areas",areas ));
                jsonObject = connector.makeHttpRequest(mainUrl, "GET", params);
                System.out.println("city json====================" + jsonObject);
                //System.out.println("area json===================="+jsonObject);
                return jsonObject;


            }
            return jsonObject;

        } catch (Exception e1) {
            System.out.println(e1);
            return null;
        }

    }

    protected void onPostExecute(JSONObject mainCategoryList) {
        /*if ((!webserviceType.equals("hitcounter")) && (!webserviceType.equals("hitCategorycounter"))) {
            dialog.dismiss();
        }*/
        try {
            dialog.dismiss();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (delegate != null)
            delegate.onTaskFisnishGettingData(mainCategoryList);

    }


}
