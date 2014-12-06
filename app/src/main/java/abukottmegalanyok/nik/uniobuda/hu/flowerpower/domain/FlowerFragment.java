package abukottmegalanyok.nik.uniobuda.hu.flowerpower.domain;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import abukottmegalanyok.nik.uniobuda.hu.flowerpower.R;

/**
 * Created by TC_L on 2014.12.06..
 */
public class FlowerFragment extends Fragment {
    Flower flower;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.flower_fragment, container, false);
        flower = (Flower) view.findViewById(R.id.flower);
        return inflater.inflate(R.layout.flower_fragment, container, false);
    }

    public void SetFlowerLevelUp(){
        flower.LevelUp();
    }

    public void SetFlowerLevelDown(){
        flower.LevelDown();
    }

    public void FragmentRefresh(){
        flower.Refresh();

    }

    public int GetFlowerLevel(){
        return flower.getLevel();
    }

}
