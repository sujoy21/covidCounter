package com.example.covid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeathFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeathFragment extends Fragment {

    OurRetrofitClient ourRetrofitClient;
    private BarChart mBarChart;
    List<CasesTimeSeries> list = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DeathFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeathFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeathFragment newInstance(String param1, String param2) {
        DeathFragment fragment = new DeathFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_death, container, false);
        mBarChart = view.findViewById(R.id.barChart2);

        getGrowthChart();
        return view;
    }

    private void getGrowthChart(){
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("https://api.covid19india.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ourRetrofitClient = retrofit.create(OurRetrofitClient.class);
        Call<Response> call = ourRetrofitClient.getData();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response.isSuccessful()){
                    com.example.covid.Response res = response.body();
                    list = res.getCasesTimeSeries();
                    List<BarEntry> barEntries = new ArrayList<>();
                    for(CasesTimeSeries obj : list){
                        String date = obj.getDate();
                        String death = obj.getDailydeceased();
                        int dat = Integer.parseInt(date.replaceAll("[^0-9]",""));
                        int dea = Integer.parseInt(death.replaceAll("[^0-9]",""));
                        barEntries.add(new BarEntry(dat,dea));
                    }

                    BarDataSet barDataSet = new BarDataSet(barEntries,"Daily Deaths");
                    barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                    BarData barData = new BarData(barDataSet);
                    barData.setBarWidth(0.9f);

                    mBarChart.setVisibility(View.VISIBLE);
                    mBarChart.animateY(3000);
                    mBarChart.setData(barData);
                    mBarChart.setFitBars(true);

                    Description description = new Description();
                    description.setText("Zoom to see the details. Will be updated in every 24hrs");
                    mBarChart.setDescription(description);
                    mBarChart.invalidate();

                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }
}
