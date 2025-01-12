/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
*/
package org.apache.cordova.inappbrowser;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

class FakeCallbackContext extends CallbackContext {
  public FakeCallbackContext() {
    super(null, null);
  }

  @Override
  public void sendPluginResult(PluginResult pluginResult) {
    // Do not send an actual result to the webview
    // Perhaps just log the result
  }
}

/**
 * Created by Oliver on 22/11/2013.
 */
public class InAppBrowserDialog extends Dialog {
    Context context;
    InAppBrowser inAppBrowser = null;

    public InAppBrowserDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    public void setInAppBroswer(InAppBrowser browser) {
        this.inAppBrowser = browser;
    }


    public void onBackPressed () {
        if (this.inAppBrowser == null) {
            this.dismiss();
        } else {
            // better to go through the in inAppBrowser
            // because it does a clean up
//             if (this.inAppBrowser.hardwareBack() && this.inAppBrowser.canGoBack()) {
//                 this.inAppBrowser.goBack();
//             }  else {
//                 this.inAppBrowser.closeDialog();
//             }

               CordovaArgs args = new CordovaArgs(new JSONArray());
                try {
                  this.inAppBrowser.execute("hide", args, new FakeCallbackContext());
               } catch (JSONException e) {
                    e.printStackTrace();
               }
        }
    }
}
