package com.deepak.magicinet;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.deepak.magicinet.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Collabrator extends Activity {

	int CAMERA_REQUEST = 1888;
	Uri selectedImage;
	int extension;
	String FileName;
	String FilePath;
	Cursor cursor;
	Bitmap photo;
	Button cap, loct, send;
	String picdata, lang, lat, before, after;
	EditText bef, aft;
	String latits;
	String longts;
	final static String tag = "DataOnServerActivity";
	LocationManager locationManager;
	ProgressDialog dialog;
	int increment;

	LocationListener mlocListener;

	public static final int DIALOG_DOWNLOAD_PROGRESS = 0;

	private ProgressDialog mProgressDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.garbage);
		cap = (Button) findViewById(R.id.capture);
		loct = (Button) findViewById(R.id.locate);
		send = (Button) findViewById(R.id.send);

		bef = (EditText) findViewById(R.id.beforetext);
		aft = (EditText) findViewById(R.id.aftertext);

		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			Toast.makeText(this, "GPS is Enabled in your devide",
					Toast.LENGTH_SHORT).show();
		} else {
			showGPSDisabledAlertToUser();

		}

		getcurrentlocation();

	}

	public void getcurrentlocation() {
		// TODO Auto-generated method stub
		loct = (Button) findViewById(R.id.locate);

		loct.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// Acquire a reference to the system Location Manager
				locationManager = (LocationManager) Collabrator.this
						.getSystemService(Context.LOCATION_SERVICE);
				// Define a listener that responds to location updates
				mlocListener = new LocationListener() {
					public void onLocationChanged(Location location) {
						// Called when a new location is found by the network
						// location provider.
						lat = Double.toString(location.getLatitude());
						lang = Double.toString(location.getLongitude());
						/*
						 * TextView tv = (TextView) findViewById(R.id.txtLoc);
						 * tv.setText("Your Location is:" + lat + "--" + lon);
						 */
						// Toast.makeText(Collabrator.this,""+lat+"---"+lang,Toast.LENGTH_LONG).show();

						TextView address = (TextView) findViewById(R.id.address);
						address.setText(GetAddress(lat, lang));
					}

					public String GetAddress(String lat, String lang) {
						// TODO Auto-generated method stub

						Geocoder geocoder = new Geocoder(Collabrator.this,
								Locale.ENGLISH);
						String ret = "";
						try {
							List<Address> addresses = geocoder.getFromLocation(
									Double.parseDouble(lat),
									Double.parseDouble(lang), 1);
							if (addresses != null) {
								Address returnedAddress = addresses.get(0);
								StringBuilder strReturnedAddress = new StringBuilder(
										"Address:\n");
								for (int i = 0; i < returnedAddress
										.getMaxAddressLineIndex(); i++) {
									strReturnedAddress.append(
											returnedAddress.getAddressLine(i))
											.append("\n");
								}
								ret = strReturnedAddress.toString();
							} else {
								ret = "No Address returned!";
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							ret = "Can't get Address!";
						}
						return ret;

					}

					public void onStatusChanged(String provider, int status,
							Bundle extras) {
					}

					public void onProviderEnabled(String provider) {
					}

					public void onProviderDisabled(String provider) {

						Toast.makeText(Collabrator.this,
								"Network Provider Currentlly Not Avaialable",
								5000).show();

					}
				};
				// Register the listener with the Location Manager to receive
				// location updates
				locationManager.requestLocationUpdates(
						LocationManager.NETWORK_PROVIDER, 0, 0, mlocListener);

			}
		});
	}

	private void showGPSDisabledAlertToUser() {
		// TODO Auto-generated method stub
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder
				.setMessage(
						"GPS is disabled in your device. Would you like to enable it?")
				.setCancelable(false)
				.setPositiveButton("Goto Settings Page To Enable GPS",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Intent callGPSSettingIntent = new Intent(
										android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
								startActivity(callGPSSettingIntent);
							}
						});
		alertDialogBuilder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = alertDialogBuilder.create();
		alert.show();

	}

	public void capture(View v) {
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		startActivityForResult(intent, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 0 && resultCode == RESULT_OK) {
			if (data != null) {

				photo = (Bitmap) data.getExtras().get("data");

				ByteArrayOutputStream bytes = new ByteArrayOutputStream();
				photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
				picdata = bytes.toByteArray() + "";
				Log.d("photo", photo + " " + picdata);

				// extension=data.getDataString().lastIndexOf(".");
				// Toast.makeText(Collabrator.this,""+picdata,5000).show();
				// extension = FilePath.substring(FilePath.lastIndexOf("."));
				Log.d("camera ---- > ", "" + data.getExtras().get("data"));
				// Toast.makeText(Collabrator.this,""+
				// data.getExtras().get("data"),5000).show();
				// Toast.makeText(Collabrator.this,""+extension,5000).show();

			}
		}
	}

	/*
	 * protected void onActivityResult(int requestCode, int resultCode, Intent
	 * imageReturnedIntent) { super.onActivityResult(requestCode, resultCode,
	 * imageReturnedIntent);
	 * 
	 * 
	 * String[] filePathColumn = { MediaStore.Images.Media.DATA,
	 * MediaStore.Images.Media.DISPLAY_NAME }; cursor =
	 * getContentResolver().query(selectedImage, filePathColumn, null, null,
	 * null);
	 * 
	 * if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) { FilePath
	 * = imageReturnedIntent.getData().getPath(); FileName =
	 * imageReturnedIntent.getData().getLastPathSegment();
	 * 
	 * Bitmap photo = (Bitmap) imageReturnedIntent.getExtras().get(
	 * "imageReturnedIntent"); ByteArrayOutputStream bytes = new
	 * ByteArrayOutputStream(); photo.compress(Bitmap.CompressFormat.JPEG, 100,
	 * bytes); picdata = bytes.toByteArray() + ""; Log.d("photo", photo + " " +
	 * picdata); int fileNameIndex = cursor.getColumnIndex(filePathColumn[1]);
	 * FileName = cursor.getString(fileNameIndex);
	 * Log.d("image name",""+FileName); extension =
	 * FilePath.substring(FilePath.lastIndexOf("."));
	 * Log.d("image extension",""+extension); } cursor.close();
	 */

	public void send(View v) {

		before = bef.getText().toString();
		after = aft.getText().toString();
		WebServiceTask task = new WebServiceTask();

		task.execute(new String[] { "http://design.ashwath.com/MobileService.asmx" });

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_DOWNLOAD_PROGRESS:
			mProgressDialog = new ProgressDialog(this);
			mProgressDialog.setMessage("Uploading file..");
			mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
			return mProgressDialog;
		default:
			return null;
		}
	}

	@SuppressLint("NewApi")
	final class WebServiceTask extends AsyncTask<String, Void, String> {

		@SuppressWarnings("deprecation")
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			showDialog(DIALOG_DOWNLOAD_PROGRESS);

		}

		@Override
		protected String doInBackground(String... urls) {
			Log.d(tag, "in doInBackground");
			String response = "";

			for (String url : urls) {
				try {

					DefaultHttpClient client = new DefaultHttpClient();
					HttpPost httpPost = new HttpPost(url);
					String payLoad = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
							+ "<SaveBinaryImage xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
							+ "<soap:Body>"
							+ "<SaveBinaryImage  xmlns=\"http://www.ashwath.com/\">"
							+ "<strFileName>"
							+ FileName
							+ "</strFileName>"

							+ "<strFileType>"
							+ extension
							+ "</strFileType>"
							+ "<imagedata>"
							+ picdata
							+ "</imagedata>"
							+ "<strLatitude>"
							+ lat
							+ "</strLatitude>"

							+ "<strLongitude>"
							+ lang
							+ "</strLongitude>"
							+ "<strBefore>"
							+ bef.getText().toString()
							+ "</strBefore>"
							+ "<strAfter>"
							+ aft.getText().toString()
							+ "</strAfter>"
							+ "</SaveBinaryImage>"
							+ "</soap:Body>"
							+ "</soap:Envelope>";

					Log.d(tag, "WSResquest: " + payLoad);
					StringEntity entity = new StringEntity(payLoad);

					httpPost.setEntity(entity);
					httpPost.addHeader("Content-Type",
							"text/xml; charset=utf-8");

					HttpResponse execute = client.execute(httpPost);
					InputStream content = execute.getEntity().getContent();
					BufferedReader buffer = new BufferedReader(
							new InputStreamReader(content));

					String s = "";
					while ((s = buffer.readLine()) != null) {

						response += s;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			Log.d(tag, "in doInBackground: response=" + response);
			return response;
		}

		protected void onProgressUpdate(String... progress) {
			Log.d("ANDRO_ASYNC", progress[0]);
			mProgressDialog.setProgress(Integer.parseInt(progress[0]));
		}

		@SuppressWarnings("deprecation")
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			dismissDialog(DIALOG_DOWNLOAD_PROGRESS);
			Log.d(tag, "WSResponse: " + result);
			AlertDialog.Builder builder = new AlertDialog.Builder(
					Collabrator.this);
			builder.setMessage("You are uploaded all Data succesfully... ")
					.setCancelable(false)
					.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									Toast.makeText(Collabrator.this,
											"Thank You..", Toast.LENGTH_LONG)
											.show();
								}
							})
					.setNegativeButton("No",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();

								}

							});
			AlertDialog alert = builder.create();
			alert.show();
		}

	}

}
