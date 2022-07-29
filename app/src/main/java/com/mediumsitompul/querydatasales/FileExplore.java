package com.mediumsitompul.querydatasales;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class FileExplore extends DialogFragment {

	// Stores names of traversed directories
	ArrayList<String> str = new ArrayList<String>();

	// Check if the first level of the directory structure is the one showing
	private Boolean firstLvl = true;

	public static final String TAG = "F_PATH";

	private Item[] fileList;
	public File path = new File(Environment.getExternalStorageDirectory() + "");
	public String chosenFile;
	public static final int DIALOG_LOAD_FILE = 1000;

	Activity activity;
	Context context;
	FragmentManager fragmentManager;
	String fileexplorerTag;

	String filePath;

	ListAdapter adapter;

	DataProvisioning_Result dataProvisioning_result ;

	Bundle bundle = new Bundle();

//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//
//		super.onCreate(savedInstanceState);
//
//		loadFileList();
//
//		showDialog(DIALOG_LOAD_FILE);
//		Log.d(TAG, path.getAbsolutePath());
//
//	}


	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		dataProvisioning_result = (DataProvisioning_Result)getActivity();
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	public void showDialog(){
		firstLvl = true;
		path = new File(Environment.getExternalStorageDirectory() + "");
		loadFileList();
		show(fragmentManager,fileexplorerTag);
	}

	public FileExplore(Activity activity, Context context, FragmentManager fragmentManager,String fileexplorerTag){
		this.activity = activity;
		this.context = context;
		this.fragmentManager = fragmentManager;
		this.fileexplorerTag = fileexplorerTag;
	}

	public void loadFileList() {
		try {
			path.mkdirs();
		} catch (SecurityException e) {
			Log.e(TAG, "unable to write on the sd card ");
		}

		// Checks whether path exists
		if (path.exists()) {
			FilenameFilter filter = new FilenameFilter() {
				@Override
				public boolean accept(File dir, String filename) {
					File sel = new File(dir, filename);
					// Filters based on whether the file is hidden or not
					return (sel.isFile() || sel.isDirectory())
							&& !sel.isHidden();

				}
			};

			String[] fList = path.list(filter);
			fileList = new Item[fList.length];
			for (int i = 0; i < fList.length; i++) {
				fileList[i] = new Item(fList[i], R.drawable.file_icon);
				if (fileList[i].file.contains(".pdf")){
					fileList[i] = new Item(fList[i], R.drawable.pdf_icon_2);
				}

				// Convert into file path
				File sel = new File(path, fList[i]);

				// Set drawables
				if (sel.isDirectory()) {
					fileList[i].icon = R.drawable.folder_icon;
					Log.d("DIRECTORY", fileList[i].file);
				} else {
					Log.d("FILE", fileList[i].file);
				}
			}

			if (!firstLvl) {
				Item temp[] = new Item[fileList.length + 1];
				for (int i = 0; i < fileList.length; i++) {
					temp[i + 1] = fileList[i];
				}
				temp[0] = new Item("Up", R.drawable.back_icon);
				fileList = temp;
			}
		} else {
			Log.e(TAG, "path does not exist");
		}

		adapter = new ArrayAdapter<Item>(context,
				android.R.layout.select_dialog_item, android.R.id.text1,
				fileList) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// creates view
				View view = super.getView(position, convertView, parent);
				TextView textView = (TextView) view
						.findViewById(android.R.id.text1);

				// put the image on the text view
				textView.setCompoundDrawablesWithIntrinsicBounds(
						fileList[position].icon, 0, 0, 0);

				// add margin between image and text (support various screen
				// densities)
				int dp5 = (int) (5 * getResources().getDisplayMetrics().density + 0.5f);
				textView.setCompoundDrawablePadding(dp5);

				return view;
			}
		};

	}

	private class Item {
		public String file;
		public int icon;

		public Item(String file, Integer icon) {
			this.file = file;
			this.icon = icon;
		}

		@Override
		public String toString() {
			return file;
		}
	}

	@NonNull
	@Override
	public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
		Dialog dialog = null;
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		if (fileList == null) {
			Log.e(TAG, "No files loaded");
			dialog = builder.create();
			return dialog;
		}

			builder.setTitle("Choose your file");
			builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					chosenFile = fileList[which].file;
					File sel = new File(path + "/" + chosenFile);
					if (sel.isDirectory()) {
						firstLvl = false;

						// Adds chosen directory to list
						str.add(chosenFile);
						fileList = null;
						path = new File(sel + "");

						loadFileList();

						dismiss();
						show(fragmentManager,fileexplorerTag);
						Log.d(TAG, path.getAbsolutePath());

					}

					// Checks if 'up' was clicked
					else if (chosenFile.equalsIgnoreCase("up") && !sel.exists()) {

						// present directory removed from list
						String s = str.remove(str.size() - 1);

						// path modified to exclude present directory
						path = new File(path.toString().substring(0,
								path.toString().lastIndexOf(s)));
						fileList = null;

						// if there are no more directories in the list, then
						// its the first level
						if (str.isEmpty()) {
							firstLvl = true;
						}
						loadFileList();

						dismiss();
						show(fragmentManager,fileexplorerTag);
						Log.d(TAG, path.getAbsolutePath());

					}
					// File picked
					else {
						path = new File(path + "/" + chosenFile);
						if (dataProvisioning_result.cekan7){
							Log.d("CEK PATH PDF", path.getAbsolutePath());
							String pdfName = chosenFile;
							String pdfPath = path.getAbsolutePath();
							Log.d("Cek Data :", pdfPath);
							dataProvisioning_result.txtPdf.setText(pdfName);
							dataProvisioning_result.pdfLink = pdfPath;
							dataProvisioning_result.cekan7 = false;
							dataProvisioning_result.updateImage.updateConditionFuncPdf(true,pdfPath,dataProvisioning_result.pseudoIdx,dataProvisioning_result.pdf_url);
						}
						dismiss();
//						path = new File(Environment.getExternalStorageDirectory() + "");
						// Perform action with file picked
					}

				}
			});
		dialog = builder.show();
		return dialog;
	}

	//	@Override
//	protected Dialog onCreateDialog(int id) {
//		Dialog dialog = null;
//		Builder builder = new Builder(context);
//
//		if (fileList == null) {
//			Log.e(TAG, "No files loaded");
//			dialog = builder.create();
//			return dialog;
//		}
//
//		switch (id) {
//		case DIALOG_LOAD_FILE:
//			builder.setTitle("Choose your file");
//			builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					chosenFile = fileList[which].file;
//					File sel = new File(path + "/" + chosenFile);
//					if (sel.isDirectory()) {
//						firstLvl = false;
//
//						// Adds chosen directory to list
//						str.add(chosenFile);
//						fileList = null;
//						path = new File(sel + "");
//
//						loadFileList();
//
//						removeDialog(DIALOG_LOAD_FILE);
//						showDialog(DIALOG_LOAD_FILE);
//						Log.d(TAG, path.getAbsolutePath());
//
//					}
//
//					// Checks if 'up' was clicked
//					else if (chosenFile.equalsIgnoreCase("up") && !sel.exists()) {
//
//						// present directory removed from list
//						String s = str.remove(str.size() - 1);
//
//						// path modified to exclude present directory
//						path = new File(path.toString().substring(0,
//								path.toString().lastIndexOf(s)));
//						fileList = null;
//
//						// if there are no more directories in the list, then
//						// its the first level
//						if (str.isEmpty()) {
//							firstLvl = true;
//						}
//						loadFileList();
//
//						removeDialog(DIALOG_LOAD_FILE);
//						showDialog(DIALOG_LOAD_FILE);
//						Log.d(TAG, path.getAbsolutePath());
//
//					}
//					// File picked
//					else {
//						// Perform action with file picked
//					}
//
//				}
//			});
//			break;
//		}
//		dialog = builder.show();
//		return dialog;
//	}

}