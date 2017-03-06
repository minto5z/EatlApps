package com.blogspot.larn4android.eatlapps.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.blogspot.larn4android.eatlapps.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

/**
 * Created by Minto on 10/11/2016.
 */
public class ThreeFragment extends Fragment {

    HashMap<String, String[]> hashMap = new HashMap<String, String[]>();
    private EditText availableFrom,details, name, phoneNumber,sqft,taka;
    private String itemDistrict, itemThana, itemRentsell;
    protected static final String TAG = "ThreeFragment";

    private  String loc;

    private ImageButton mSelectImage;
    private static final int GALLERY_REQUST = 1;
    private Button mSelectedBtn;
    private Uri imageUri =null;
    private ProgressDialog mProgress;

    private DatabaseReference mDatabase;
    private StorageReference mStorage;



    public ThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_apartment_advertisment, container, false);




        final Spinner districtSpinner = (Spinner) v.findViewById(R.id.district_spinner);
        final Spinner thanaSpinner = (Spinner) v.findViewById(R.id.thana_spinner);
        final Spinner rentsellSpinner = (Spinner) v.findViewById(R.id.rent_sell_spinner);

//
//        String rent_sell[] = {"বিক্রয়", "ভাড়া"};
//        String district[] = {"টাঙ্গাইল", "ফরিদপুর", "মানিকগঞ্জ", "কিশোরগঞ্জ", "মুন্সীগঞ্জ", "নরসিংদী", "টঙ্গী", "গোপালগঞ্জ", "শরীয়তপুর", "মাদারীপুর", "রাজবাড়ী", "আরিচা", "ভৈরব", "ধনবাড়ী", "মির্জাপুর", "মধুপুর", "কালিহাতী", "ঘাটাইল", "ভূয়াপুর", "গোপালপুর", "সখিপুর", "বাসাইল", "গাজীপুর", "নারায়ণগঞ্জ"};
//
//        String thana[] = {"কক্সবাজার", "বান্দরবান", "খাগড়াছড়ি", "রাঙ্গামাটি", "রাঙ্গুনিয়া", "সন্দ্বীপ", "ফটিকছড়ি", "নাজির হাট", "বারোইয়ারহাট", "মিরসরাই", "সীতাকুন্ড", "হাটহাজারী", "রাউজান", "পটিয়া", "চন্দনাইশ", "লোহাগাড়া", "সাতকানিয়া", "বোয়ালখালী", "আখাউড়া", "ব্রাহ্মণবাড়িয়া", "সরাইল", "শাহবাজপুর", "চাঁদপুর", "চৌমুহনী", "ফেনী", "লাকসাম", "লক্ষ্মীপুর", "নোয়াখালী", "বুড়িচং", "কুমিল্লা"};
//
//        String kisorthana[] = {"ময়মনসিংহ", "জামালপুর", "সম্ভুগঞ্জ", "মুক্তাগাছা", "ভালুকা", "গৌরীপুর", "ফুলপুর", "ত্রিশাল", "নান্দাইল", "গফরগাঁও", "ঈশ্বরগঞ্জ", "হালুয়াঘাট", "ফুলবাড়িয়া", "নেত্রকোনা", "শেরপুর", "ধোবাউড়া", "তারাকান্দা"};
//        String manikthana[] = {"পাহাড়তলী থানা", "চাঁদগাও থানা", "বন্দর থানা", "ডবলমুরিং থানা", "কোতোয়ালী থানা", "পাঁচলাইশ থানা", "বায়েজীদ থানা", "পতেঙ্গা থানা", "হালিশহর থানা", "খুলশী থানা", "বাকলিয়া থানা", "কর্ণফুলী থানা", "চকবাজার থানা", "আকবর শাহ থানা", "সদরঘাট থানা", "ইপিজেড থানা"};
//        // Spinner Drop down elements

        String rent_sell[] = {"Sale", "Rent"};
        String district[] = {"Tangail", "Faridpur", "Manikgong", "Kishorgong", "Monshigong", "Norshindi", "Tagy", "Gopalgong"};

        String thana[] = {"Comilla","Chittagong","Tangail", "Faridpur", "Manikgong", "Kishorgong", "Monshigong", "Norshindi", "Tagy", "Gopalgong"};

        String kisorthana[] = {"Maymonshing","Tangail", "Faridpur", "Manikgong", "Kishorgong", "Monshigong", "Norshindi", "Tagy", "Gopalgong"};
        String manikthana[] = {"Dinajpur","Ghoraghat","Bogora","Tangail", "Faridpur", "Manikgong", "Kishorgong", "Monshigong", "Norshindi", "Tagy", "Gopalgong"};
        // Spinner Drop down elements
//
//

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, district);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        districtSpinner.setAdapter(dataAdapter);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, rent_sell);

        // Drop down layout style - list view with radio button
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        rentsellSpinner.setAdapter(adapter);

        //String item = String.valueOf(spinner.getSelectedItem());

        hashMap.put("Tangail", thana);
        hashMap.put("Faridpur", manikthana);
        hashMap.put("Manikgong", kisorthana);
        hashMap.put("Kishorgong", kisorthana);
        districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemDistrict = parent.getItemAtPosition(position).toString();
                String[] nameDis = hashMap.get(itemDistrict);
                ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, nameDis);
                dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                thanaSpinner.setAdapter(dataAdapter1);
               // Toast.makeText(parent.getContext(), "Selected: " + itemDistrict, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        thanaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemThana = parent.getItemAtPosition(position).toString();
              //  Toast.makeText(parent.getContext(), "Selected: " + itemThana, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        rentsellSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemRentsell = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + itemRentsell, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//        if(itemRentsell!=null&&itemRentsell.equals("Sale")){
//            if(itemDistrict.equals("Faridpur")) {
//                mDatabase = FirebaseDatabase.getInstance().getReference().child("EasySearch/Apartment/Sale/District/Faridpur");
//            }
//            if(itemDistrict.equals("Tangail")) {
//                mDatabase = FirebaseDatabase.getInstance().getReference().child("EasySearch/Apartment/Sale/District/Tangail");
//            }
//        }
//        if(itemRentsell!=null&&itemRentsell.equals("Rent")){
//            if(itemDistrict.equals("Faridpur")) {
//                mDatabase = FirebaseDatabase.getInstance().getReference().child("EasySearch/Apartment/Rent/District/Faridpur");
//            }
//            if(itemDistrict.equals("Tangail")) {
//                mDatabase = FirebaseDatabase.getInstance().getReference().child("EasySearch/Apartment/Rent/District/Tangail");
//            }
//        }

        mSelectImage = (ImageButton) v.findViewById(R.id.btn_imageOne);


        availableFrom = (EditText) v.findViewById(R.id.et_availableFrom);
        details = (EditText) v.findViewById(R.id.et_details);
        name = (EditText) v.findViewById(R.id.et_companyName);
        phoneNumber = (EditText) v.findViewById(R.id.et_phoneNumber);
        taka = (EditText)v.findViewById(R.id.et_taka);
        sqft = (EditText)v.findViewById(R.id.et_sqft);
        mSelectedBtn = (Button) v.findViewById(R.id.btn_submitted);

        mProgress = new ProgressDialog(this.getActivity());
        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUST);
            }
        });
        mSelectedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPosting(v);
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLERY_REQUST && resultCode==this.getActivity().RESULT_OK){
            imageUri = data.getData();
            mSelectImage.setImageURI(imageUri);
        }
    }
    private void startPosting(final View view) {

        mProgress.setMessage("Loading....");
        final String var_availablefrom = availableFrom.getText().toString().trim();
        final String var_details = details.getText().toString().trim();
        final String var_companyname = name.getText().toString().trim();
        final String var_phonenumber = phoneNumber.getText().toString().trim();
        final String var_taka = taka.getText().toString().trim();
        final String var_sqft = sqft.getText().toString().trim();
        if(!TextUtils.isEmpty(var_availablefrom)
                &&!TextUtils.isEmpty(var_details)
                &&!TextUtils.isEmpty(var_companyname)
                &&!TextUtils.isEmpty(var_phonenumber)
                &&!TextUtils.isEmpty(itemDistrict)
                &&!TextUtils.isEmpty(itemThana)
                &&!TextUtils.isEmpty(itemRentsell)
                &&!TextUtils.isEmpty(var_taka)
                &&!TextUtils.isEmpty(var_sqft)
                &&imageUri!= null ){
            mProgress.show();

            mStorage = FirebaseStorage.getInstance().getReference();
            loc = new String("EasySearch/Apartment/"+ itemRentsell+"/District/"+ itemDistrict);

            Log.d(TAG,loc);
            //"EasySearch/Apartment/Sale/District/Faridpur"
            mDatabase = FirebaseDatabase.getInstance().getReference().child(loc);


            StorageReference filePath = mStorage.child("apartmentImage").child(imageUri.getLastPathSegment());

            filePath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Uri downloadUri = taskSnapshot.getDownloadUrl();
                    DatabaseReference newPost = mDatabase.push();
                    newPost.child("avilablefrom").setValue(var_availablefrom);
                    newPost.child("name").setValue(var_companyname);
                    newPost.child("details").setValue(var_details);
                    newPost.child("district").setValue(itemDistrict);
                    newPost.child("picture").setValue(downloadUri.toString());
                    newPost.child("phone").setValue(var_phonenumber);
                    newPost.child("rentsale").setValue(itemRentsell);
                    newPost.child("thana").setValue(itemThana);
                    newPost.child("taka").setValue(var_taka);
                    newPost.child("squrefit").setValue(var_sqft);


                    mProgress.dismiss();
                    availableFrom.setText("");
                    details.setText("");
                    name.setText("");
                    phoneNumber.setText("");
                    taka.setText("");
                    sqft.setText("");
                    mSelectImage.setImageURI(null);


                }
            });
        }

    }
}