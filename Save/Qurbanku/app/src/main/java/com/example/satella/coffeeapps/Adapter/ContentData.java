package com.example.satella.coffeeapps.Adapter;

import com.example.satella.coffeeapps.Model.Content;

import java.util.ArrayList;

public class ContentData {
    public static String[][] data = new String[][]{
            {"Domba Aji",
                    "Rp.2.000.000",
                    "Domba sehat tak cacat, warna putih bersih\n" +
                    "\n\n  Berat Badan : 160kg", "https://vernolmanda23.files.wordpress.com/2017/01/deg.jpg"},

            {"Domba Garut",
                    "Rp.1.600.000",
                    "Daging Unggulan, rasa mantap\n" +
                            "\n\n Berat Badan : 170 kg", "http://www.kambingkiloanciledug.com/image-product/img39-1550718067.jpg"},

            {"Kambing Gibas",
                    "Rp. 1.800.000",
                    "Terjamin sehat, kekar, dan berdaging banyak\n" +
                            "\n\nsBerat Badan 140 KG", "hhttps://1.bp.blogspot.com/-nps64B0BlpI/Wq4a-tKBaBI/AAAAAAAAHeM/qTF5IF9Y-bAKLkYR5Lsu4hBRB1mH8pgqgCLcBGAs/s1600/Kambing%2BGibas.PNG"},

            {"Kambing Khas Malang",
                    "Rp.1.400.000",
                    "Kecil-kecil rasa mantap\n" +
                            "\n\nBerat badan : 120 KG\n", "https://ngalam.co/wp-content/uploads/2017/08/Kambing-Qurban-OK.jpg"},

            {"Kambing Istimewa",
                    "Rp.1.600.000",
                    "Terima jasa untuk arisan\n" +
                            "\n\n Berat Badan 150 KG\n", "https://www.islampos.com/wp-content/uploads/2017/08/kambing-qurban.jpg"},
    };


    public static ArrayList<Content> getListData() {
        ArrayList<Content> list = new ArrayList<>();
        for (String[] aData : data) {
            Content content = new Content();
            content.setName(aData[0]);
            content.setHarga(aData[1]);
            content.setDesc(aData[2]);
            content.setPhoto(aData[3]);


            list.add(content);
        }
        return list;
    }
}
