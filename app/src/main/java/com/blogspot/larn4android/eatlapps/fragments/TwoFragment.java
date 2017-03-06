package com.blogspot.larn4android.eatlapps.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.blogspot.larn4android.eatlapps.R;
import com.blogspot.larn4android.eatlapps.apartment.AllApartment;
import com.yahoo.mobile.client.android.util.rangeseekbar.RangeSeekBar;

import java.util.HashMap;

/**
 * Created by Minto on 10/11/2016.
 */
public class TwoFragment extends Fragment {

    private String itemDistrict, itemThana;

    private HashMap<String, String[]> hashMap = new HashMap<String, String[]>();
    private int mintaka,minsqft,cheakrent;
    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_two, container, false);


        final Spinner districtSpinner = (Spinner) v.findViewById(R.id.sale_district_spinner);
        final Spinner thanaSpinner = (Spinner) v.findViewById(R.id.sale_thana_spinner);
        // final Spinner rentsellSpinner = (Spinner) v.findViewById(R.id.rent_sell_spinner);

//
//        String rent_sell[] = {"বিক্রয়", "ভাড়া"};
//        String district[] = {"টাঙ্গাইল", "ফরিদপুর", "মানিকগঞ্জ", "কিশোরগঞ্জ", "মুন্সীগঞ্জ", "নরসিংদী", "টঙ্গী", "গোপালগঞ্জ", "শরীয়তপুর", "মাদারীপুর", "রাজবাড়ী", "আরিচা", "ভৈরব", "ধনবাড়ী", "মির্জাপুর", "মধুপুর", "কালিহাতী", "ঘাটাইল", "ভূয়াপুর", "গোপালপুর", "সখিপুর", "বাসাইল", "গাজীপুর", "নারায়ণগঞ্জ"};
//
//        String thana[] = {"কক্সবাজার", "বান্দরবান", "খাগড়াছড়ি", "রাঙ্গামাটি", "রাঙ্গুনিয়া", "সন্দ্বীপ", "ফটিকছড়ি", "নাজির হাট", "বারোইয়ারহাট", "মিরসরাই", "সীতাকুন্ড", "হাটহাজারী", "রাউজান", "পটিয়া", "চন্দনাইশ", "লোহাগাড়া", "সাতকানিয়া", "বোয়ালখালী", "আখাউড়া", "ব্রাহ্মণবাড়িয়া", "সরাইল", "শাহবাজপুর", "চাঁদপুর", "চৌমুহনী", "ফেনী", "লাকসাম", "লক্ষ্মীপুর", "নোয়াখালী", "বুড়িচং", "কুমিল্লা"};
//
//        String kisorthana[] = {"ময়মনসিংহ", "জামালপুর", "সম্ভুগঞ্জ", "মুক্তাগাছা", "ভালুকা", "গৌরীপুর", "ফুলপুর", "ত্রিশাল", "নান্দাইল", "গফরগাঁও", "ঈশ্বরগঞ্জ", "হালুয়াঘাট", "ফুলবাড়িয়া", "নেত্রকোনা", "শেরপুর", "ধোবাউড়া", "তারাকান্দা"};
//        String manikthana[] = {"পাহাড়তলী থানা", "চাঁদগাও থানা", "বন্দর থানা", "ডবলমুরিং থানা", "কোতোয়ালী থানা", "পাঁচলাইশ থানা", "বায়েজীদ থানা", "পতেঙ্গা থানা", "হালিশহর থানা", "খুলশী থানা", "বাকলিয়া থানা", "কর্ণফুলী থানা", "চকবাজার থানা", "আকবর শাহ থানা", "সদরঘাট থানা", "ইপিজেড থানা"};
//        // Spinner Drop down elements

        //String rent_sell[] = {"Sale", "Rent"};
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


        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, rent_sell);

        // Drop down layout style - list view with radio button
        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        //rentsellSpinner.setAdapter(adapter);

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
                //Toast.makeText(parent.getContext(), "Selected: " + itemThana, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        RangeSeekBar rangeSeekBarBDT = (RangeSeekBar)v.findViewById(R.id.sale_rangeSeekbar);
        RangeSeekBar rangeSeekBarSqft = (RangeSeekBar)v.findViewById(R.id.sale_rangeSeekbar1);

        final TextView tv_bdtmin = (TextView)v.findViewById(R.id.tv_bdtmin);
        final TextView tv_bdtmax = (TextView)v.findViewById(R.id.tv_bdtmax);
        final TextView tv_sqftmax = (TextView)v.findViewById(R.id.tv_sqftmax);
        final TextView tv_sqftmin = (TextView)v.findViewById(R.id.tv_sqftmin);
        rangeSeekBarBDT.setRangeValues(0, 1000000);
        rangeSeekBarBDT.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                tv_bdtmin.setText(minValue.toString());
                tv_bdtmax.setText(maxValue.toString());
                mintaka = minValue;;
            }
        });
        rangeSeekBarBDT.setNotifyWhileDragging(true);

        rangeSeekBarSqft.setRangeValues(0, 20000);
        rangeSeekBarSqft.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                tv_sqftmin.setText(minValue.toString());
                tv_sqftmax.setText(maxValue.toString());
                minsqft = minValue;
            }
        });
        rangeSeekBarSqft.setNotifyWhileDragging(true);

        Button btn = (Button)v.findViewById(R.id.btn_sale_search);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToNewActivity();
            }
        });



        return v;
    }

    private void moveToNewActivity() {
        Bundle bundle = new Bundle();
        bundle.putInt("taka",mintaka);
        bundle.putInt("rent",0);
        bundle.putString("district",itemDistrict);
        bundle.putInt("sqft",minsqft);
        Intent i = new Intent(getActivity(), AllApartment.class);
        i.putExtras(bundle);
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(0,0);

    }
}