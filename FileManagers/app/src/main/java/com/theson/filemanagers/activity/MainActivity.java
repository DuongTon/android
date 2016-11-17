package com.theson.filemanagers.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.theson.filemanagers.R;
import com.theson.filemanagers.adapter.ItemAdapter;
import com.theson.filemanagers.adapter.MenusAdapter;
import com.theson.filemanagers.manager.FileManager;
import com.theson.filemanagers.model.ItemDialog;
import com.theson.filemanagers.model.ItemDialogHome;
import com.theson.filemanagers.model.ItemDialogNewFolder;
import com.theson.filemanagers.model.ItemDialogRename;
import com.theson.filemanagers.model.Items;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener,
        ItemDialog.OnReceiveDataListener, ItemDialogHome.OnReceiveDataListener {

    private DrawerLayout drawerLayout;

    private ListView lvContent;
    private TextView txtPaste;
    private ItemAdapter itemAdapter;
    private FileManager fileManager;

    private File file;
    private String getPaths;
    private String fileName;
    private int index;
    private String str;
    private ArrayList<Items> arrItems;
    private ItemDialogHome dialogHome;
    private boolean isTrue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
    }

    private void initializeComponents() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ListView navigation = (ListView) findViewById(R.id.navigation);
        ImageView btnOpenMenu = (ImageView) findViewById(R.id.btnOpenMenu);
        ImageView btnHome = (ImageView) findViewById(R.id.btnHome);
        btnHome.setOnClickListener(this);
        MenusAdapter menuAdapter = new MenusAdapter();
        navigation.setAdapter(menuAdapter);
        btnOpenMenu.setOnClickListener(this);
        navigation.setOnItemClickListener(this);
        lvContent = (ListView) findViewById(R.id.list_view_content);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        lvContent.setOnItemClickListener(this);
        lvContent.setOnItemLongClickListener(this);
        lvContent.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);
        getFile(Environment.getExternalStorageDirectory().getAbsolutePath());
        dialogHome = new ItemDialogHome(this);
    }



    private void getFile(String path) {
        arrItems = new ArrayList<>();
        file = new File(path);
        getPaths = file.getParent();
        File[] listFile = file.listFiles();
        if (file.isFile()) {
            Toast.makeText(this, "File không tồn tại", Toast.LENGTH_SHORT).show();
        } else {
            if (file.isDirectory()) {
                if (listFile.length == 0) {
                    Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
                }
                for (File file : listFile) {
                    Date lastModDate = new Date(file.lastModified());
                    DateFormat formater = DateFormat.getDateTimeInstance();
                    String date_modify = formater.format(lastModDate);
                    String size = getSize(file);
                    if (file.getName().endsWith(".mp3")) {
                        arrItems.add(new Items(R.drawable.ic_music, file.getName(), date_modify, size, file.getAbsolutePath()));
                    } else if (file.getName().endsWith(".mp4")) {
                        arrItems.add(new Items(R.drawable.ic_play, file.getName(), date_modify, size, file.getAbsolutePath()));
                    } else if (file.getName().endsWith(".png") || file.getName().endsWith(".jpg") || file.getName().endsWith(".jpg")
                            || file.getName().endsWith(".jpeg") || file.getName().endsWith(".gif")) {
                        arrItems.add(new Items(R.drawable.ic_photo, file.getName(), date_modify,size, file.getAbsolutePath()));
                    } else if (file.getName().endsWith(".pdf") || file.getName().endsWith(".txt") ||
                            file.getName().endsWith(".xml") || file.getName().endsWith(".doc") ||
                            file.getName().endsWith(".xls") || file.getName().endsWith(".xlsx")) {
                        arrItems.add(new Items(R.drawable.ic_adobe, file.getName(), date_modify,size, file.getAbsolutePath()));
                    } else {
                        arrItems.add(new Items(R.drawable.ic_folder, file.getName(), date_modify, size, file.getAbsolutePath()));
                    }
                }
            }
        }
        Collections.sort(arrItems);
        fileManager = new FileManager(arrItems);
        itemAdapter = new ItemAdapter();
        lvContent.setAdapter(itemAdapter);
        lvContent.setSelection(index);
    }



    public String getSize(File file) {
        String value;
        long fileSize = file.length() / 1024;
        if (fileSize >= 1024)
            value = fileSize / 1024 + " Mb";
        else
            value = fileSize + " kb";
        return value;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOpenMenu:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.btnHome:
                customDialog(dialogHome);
                dialogHome.setOnReceiveDataListener(this);
                dialogHome.show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.navigation:
                if (position == 0) {
                    break;
                } else {
                    ArrayList<String> arr = new ArrayList<>();
                    arr.add("sdcard" + "/" + "download");
                    arr.add("sdcard");
                    arr.add("sdcard" + "/" + "dcim");
                    arr.add("sdcard" + "/" + "download");
                    arr.add("sdcard" + "/" + "zingmp3");
                    arr.add("sdcard" + "/" + "pictures");
                    arr.add("sdcard" + "/" + "pictures");
                    arr.add("sdcard" + "/" + "pictures");
                    arr.add("sdcard" + "/" + "pictures");
                    arr.add("sdcard" + "/" + "pictures");
                    arr.add("sdcard" + "/" + "pictures");
                    for (int i = 0; i < arr.size(); i++) {
                        getFile(arr.get(position));
                    }
                    drawerLayout.closeDrawers();
                }
                break;

            case R.id.list_view_content:
                openApplication(position);
                index = position;
                break;

            default:
                break;
        }
    }

    public void openApplication(int position) {
        Items o = fileManager.getFile(position);
        if (o.getName().endsWith(".mp3")) {
            Intent intent = new Intent();
            intent.setAction(android.content.Intent.ACTION_VIEW);
            file = new File(o.getPath());
            intent.setDataAndType(Uri.fromFile(file), "audio/*");
            startActivity(intent);
        } else if (o.getName().endsWith(".png") || o.getName().endsWith(".jpg") || o.getName().endsWith(".JPG")
                || o.getName().endsWith(".jpeg") || o.getName().endsWith(".gif")) {
            Intent intent = new Intent();
            intent.setAction(android.content.Intent.ACTION_VIEW);
            file = new File(o.getPath());
            intent.setDataAndType(Uri.fromFile(file), "image/*");
            startActivity(intent);
        } else if (o.getName().endsWith(".mp4")) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            file = new File(o.getPath());
            intent.setDataAndType(Uri.fromFile(file), "video/*");
            startActivity(intent);
        } else if (o.getName().endsWith(".pdf") || o.getName().endsWith(".txt") ||
                o.getName().endsWith(".xml") || o.getName().endsWith(".doc") ||
                o.getName().endsWith(".xls") || o.getName().endsWith(".xlsx")) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            file = new File(o.getPath());
            intent.setDataAndType(Uri.fromFile(file), "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        } else {
            getFile(o.getPath());
            lvContent.invalidateViews();
            itemAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        fileName = arrItems.get(position).getName();
        ItemDialog dialog = new ItemDialog(this);
        customDialog(dialog);
        dialog.setOnReceiveDataListener(this);
        dialog.show();
        return true;
    }

    public void customDialog(Dialog dialog) {
        WindowManager.LayoutParams wml = dialog.getWindow().getAttributes();
        wml.gravity = Gravity.RIGHT | Gravity.TOP;
        wml.x = 20;
        wml.y = 100;
    }


    @Override
    public void OnDataReceiveData(String data) {
        switch (data) {
            case ItemDialog.TXT_COPY:
                txtPaste = dialogHome.getTxtPaste();
                txtPaste.setEnabled(true);
                txtPaste.setTextColor(0xff000000);
                str = file.getAbsolutePath() + "/" + fileName;
                break;

            case ItemDialog.TXT_CUT:
                isTrue = true;
                str = file.getAbsolutePath() + "/" + fileName;
                txtPaste = dialogHome.getTxtPaste();
                txtPaste.setEnabled(true);
                txtPaste.setTextColor(0xff000000);
                break;

            case ItemDialog.TXT_DELETE:
                openDialogQuestionDelete();
                break;

            case ItemDialog.TXT_RENAME:
                ItemDialogRename dialogRename = new ItemDialogRename(this, fileName);
                dialogRename.setOnClickSaveListener(new ItemDialogRename.OnClickSaveListener() {
                    @Override
                    public void OnClickSave(String name) {
                        renameFile(fileName, name);
                        getFile(file.getAbsolutePath());
                        itemAdapter.notifyDataSetChanged();
                    }
                });
                dialogRename.show();
                break;

            default:
                break;
        }
    }

    public void renameFile(String oldName, String newName) {
        file = new File(file.getAbsolutePath());
        if (file.exists()) {
            File from = new File(file, oldName);
            File to = new File(file, newName);
            if (from.exists())
                from.renameTo(to);
        }
    }

    @Override
    public void OnReceiveData(String data) {
        switch (data) {
            case ItemDialogHome.TXT_NEW_FOLDER:
                ItemDialogNewFolder dialogNewFolder = new ItemDialogNewFolder(this);
                dialogNewFolder.setOnClickButtonListener(new ItemDialogNewFolder.OnClickButtonListener() {
                    @Override
                    public void onClickButton(String name) {
                        File file1 = new File(file.getAbsolutePath(), name);
                        if (!file1.exists()) {
                            file1.mkdirs();
                            getFile(file.getAbsolutePath());
                            Toast.makeText(MainActivity.this, "New Folder Created", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialogNewFolder.show();
                break;
            case ItemDialogHome.TXT_PASTE:
                File file1 = new File(str);
                File file2 = new File(file.getAbsolutePath() + "/" + fileName);
                if(isTrue){
                    moveFile(file1,file2);
                    Toast.makeText(this, "Cut Success", Toast.LENGTH_SHORT).show();
                    isTrue = false;
                }else {
                    copyFile(file1, file2);
                    Toast.makeText(this, "Copy Success", Toast.LENGTH_SHORT).show();
                }
                getFile(file.getAbsolutePath());
                txtPaste.setEnabled(false);
                txtPaste.setTextColor(0xffababab);
                break;
            default:
                break;
        }
    }

    private void copyFile(File sourceLocation, File targetLocation) {
        try {
            if (sourceLocation.isDirectory()) {
                if (!targetLocation.exists()) {
                    targetLocation.mkdir();
                }
                String[] children = sourceLocation.list();
                for (int i = 0; i < sourceLocation.listFiles().length; i++) {
                    moveFile(new File(sourceLocation, children[i]), new File(targetLocation, children[i]));
                }
            } else {
                InputStream in = new FileInputStream(sourceLocation);
                OutputStream out = new FileOutputStream(targetLocation);
                byte[] buffer = new byte[1024 * 1024];
                int read;
                while ((read = in.read(buffer)) > 0) {
                    out.write(buffer, 0, read);
                }
                in.close();
                out.flush();
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void moveFile(File sourceLocation, File targetLocation) {
        copyFile(sourceLocation,targetLocation);
        deleteDirectory(sourceLocation);
    }


    private void openDialogQuestionDelete() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setCancelable(true)
                .setTitle("Delete file")
                .setMessage("Do you want to delete?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        File file1 = new File(file.getAbsolutePath() + "/" + fileName);
                        deleteDirectory(file1);
                        getFile(file.getAbsolutePath());
                        Toast.makeText(MainActivity.this,"delete success", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        alertDialog.show();
    }

    public void deleteDirectory(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory())
        {
            for (File child : fileOrDirectory.listFiles())
            {
                deleteDirectory(child);
            }
        }

        fileOrDirectory.delete();
    }

    @Override
    public void onBackPressed() {
        if (getPaths == null) {
            finish();
        } else {
            if (getPaths.equals(new File(Environment.getExternalStorageDirectory().getAbsolutePath()).getParent())) {
                super.onBackPressed();
            } else {
                getFile(getPaths);
            }
        }
    }
}

