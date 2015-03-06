package jp.ac.anan_nct.smaoni_elide.model;

import android.location.Location;

import java.util.ArrayList;

/**
 * Created by skriulle on 2015/03/02.
 */
public class Field {
    private ArrayList<Location> points;
    public double maxLng, minLng, maxLat, minLat;

    private int fieldType;
    //0->Meratoric field, 1->strict appointed field

    public Field() {
        points = new ArrayList<Location>();
        maxLng = maxLat = Double.MIN_VALUE;
        minLng = minLat = Double.MAX_VALUE;
        fieldType = 0;
    }

    public Field(ArrayList<Location> points, int fieldType) {
        this.fieldType = fieldType;
        this.points = points;
        setField();
    }

    public Field(double maxLng, double minLng, double maxLat, double minLat) {
        this();
        this.maxLng = maxLng;
        this.minLng = minLng;
        this.maxLat = maxLat;
        this.minLat = minLat;
    }

    public ArrayList<Location> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Location> points) {
        this.points = points;
    }

    public void addPoints(Location location) {
        points.add(location);
    }

    public void setField() {
        switch (fieldType) {
            case 0:
                setField0(points);
                break;
            case 1:
                setField1(points);
                break;
        }
    }

    private void setField0(ArrayList<Location> points) {
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).getLongitude() > maxLng) maxLng = points.get(i).getLongitude();
            else if (points.get(i).getLongitude() < minLng) minLng = points.get(i).getLongitude();
            if (points.get(i).getLatitude() > maxLat) maxLat = points.get(i).getLatitude();
            else if (points.get(i).getLatitude() < minLat) minLat = points.get(i).getLatitude();
        }
    }

    private void setField1(ArrayList<Location> points) {
        //TODO
    }


    public void setFieldType(int fieldtype) {
        this.fieldType = fieldtype;
    }

    public int getFieldType() {
        return fieldType;
    }


    public boolean fieldCheck() {
        if (maxLat <= minLat || maxLng <= minLng) return false;
        return true;
    }


}
