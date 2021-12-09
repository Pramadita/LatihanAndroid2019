package com.example.satella.coffeeapps.Adapter;

import com.example.satella.coffeeapps.Model.Content;

import java.util.ArrayList;

public class ContentData {
    public static String[][] data = new String[][]{
            {"01",
                    "Cicaheum - Kebon Kelapa",
                    "->TERMINAL KEBON KELAPA - Jl. Dewi Sartika - Jl. Kautamaan Istri - Jl. Pungkur - Jl. Karapitan - Universitas Langlangbuana (Karapitan) - Jl. Buah Batu - Jl. Banteng - Jl. Sancang - Jl. Lodaya - Jl. Martanegara - Jl. Turangga - Jl. Gatot Subroto - TSM - Binong - Jl. Kiara Condong - Pasar Kiara Condong - STASIUN KIARA CONDONG - Jl. Jakarta - Jl. WR. Supratman - SMP NEGERI 14 - Taman WR. Supratman - Jl. Katamso - Jl. Pahlawan - KLINIK Kebidanan Kartini (Perempatan Pahlawan) - ITENAS - Taman Makam Pahlawan Cikutra - Jl. Cikutra - GOR Citra - Jl. PHH. Mustofa (Suci) - TERMINAL CICAHEUM\n" +
                    "\n\n<--TERMINAL CICAHEUM - Jl. PHH. Mustofa (Suci) - KLINIK Kebidanan Kartini (Perempatan Pahlawan) - Jl. Pahlawan - Jl. Katamso - Jl. WR. Supratman - Taman WR. Supratman - SMP NEGERI 14 - Jl. Ahmad Yani - Cicadas - Jl. Jakarta - Jl. Kiara Condong - STASIUN KIARA CONDONG - PASAR KIARA CONDONG - Binong - TSM - Jl. Turangga - Jl. Martanegara - Jl. Lodaya - Jl. Gajah - Jl. Buah Batu - Jl. Gurame - Jl. Moh. Ramdan - Jl. BKR - Jl. Moh. Toha - Jl. Dewi Sartika - SMP NEGERI 10 - SMP NEGERI 3 - TERMINAL KEBON KELAPA", "-6.9276166,107.6037241", "http://transportasiumum.com/wp-content/uploads/2017/03/Abdul-Muis-Cicaheumbinong.gif", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9f/Flag_of_Indonesia.svg/800px-Flag_of_Indonesia.svg.png"},

            {"32",
                    "Cicadas - Cibiru",
                    "-->Cicadas (Ahmad Yani) - Jl. Kiara Condong - Jl. Sukarno-Hatta - Margahayu Raya (Sukarno-Hatta) - Metro (Sukarno-Hatta) - Riung Bandung (Sukarno-Hatta) - Pasar Induk Gede Bage (Sukarno-Hatta) - Bunderan Cibiru\n" +
                            "\n\n<--Bunderan Cibiru - Jl. Sukarno-Hatta - Pasar Induk Gede Bage (Sukarno-Hatta) - Riung Bandung (Sukarno-Hatta) - Metro (Sukarno-Hatta) - Margahayu Raya (Sukarno-Hatta) - Jl. Kiara Condong - Jl. Jakarta - Jl. Ahmad Yani - Cicadas (Ahmad Yani)", "-6.9351303,107.7152746", "http://transportasiumum.com/wp-content/uploads/2017/03/St-Hall-Ciumbuleuitlurus.gif", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9f/Flag_of_Indonesia.svg/800px-Flag_of_Indonesia.svg.png"},

            {"31",
                    "Antapani - Ciroyom",
                    "-->Terminal Antapani - Jl. Cibatu (Antapani) - Jl. Purwakarta (Antapani) - Jl. Jakarta - Jl. Sukabumi - Jl. Laswi - Stadion Persib (Ahmad Yani) - Jl. Ahmad Yani - Jl. Gudang Utara - Jl. Bangka - Jl. Belitung - Jl. Sumatera - Jl. Aceh - Jl. Merdeka - Jl. Perintis Kemerdekaan - Wastu Kencana - Jl. Pajajaran - Jl. Cicendo - Jl. Kebon Kawung - Stasiun Bandung (Kebon Kawung) - Jl. Pasir Kaliki - Istana Plaza (Pajajaran) - Jl. Pajajaran - Jl. Abdul Rahman Saleh - Jl. Garuda - Jl. Ciroyom - Terminal Ciroyom\n" +
                            "\n\n<--Terminal Ciroyom - Jl. Ciroyom - Jl. Arjuna - Jl. Pajajaran - Istana Plaza (Pajajaran) - Jl. Cihampelas - Jl. Wastu Kencana - Jl. RE. Martadinata - Jl. Purnawarman - BEC (Purnawarman) - Jl. Wastu Kencana - Jl. Aceh - Jl. Kalimantan - Jl. Belitung - Jl. Bangka - Jl. Gudang Utara - Jl. Ahmad Yani - Stadion Persib (Ahmad Yani)", "-6.9140309,107.5853838", "http://transportasiumum.com/wp-content/uploads/2017/10/Antapani-Ciroyom.gif", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9f/Flag_of_Indonesia.svg/800px-Flag_of_Indonesia.svg.png"},

            {"29",
                    "Kebon Kelapa - Mengger",
                    "-->Terminal Kebon Kelapa – Mengger\n" +
                            "\n\n<--Mengger - Terminal Kebon Kelapa\n", "-6.9592008,107.6250291", "http://transportasiumum.com/wp-content/uploads/2017/03/Abdul-Muis-ElangMengger.gif", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9f/Flag_of_Indonesia.svg/800px-Flag_of_Indonesia.svg.png"},

            {"05",
                    "Cicaheum - Ledeng",
                    "-->TERMINAL LEUWI PANJANG - Jl. Sukarno-Hatta - Jl. Kiara Condong - PASAR KIARA CONDONG - STASIUN KIARA CONDONG - Jl. Jakarta - Antapani - TERMINAL CICAHEUM (belakang)\n" +
                            "\n\n<--TERMINAL CICAHEUM (belakang) - Antapani - Jl. Jakarta - Jl. Kiara Condong - Jl. Sukarno-Hatta - TERMINAL LEUWI PANJANG", "-6.9027075,107.6526844", "http://transportasiumum.com/wp-content/uploads/2017/03/Cicaheum-Ledeng.gif", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9f/Flag_of_Indonesia.svg/800px-Flag_of_Indonesia.svg.png"},
    };


    public static ArrayList<Content> getListData() {
        ArrayList<Content> list = new ArrayList<>();
        for (String[] aData : data) {
            Content content = new Content();
            content.setName(aData[0]);
            content.setFrom(aData[1]);
            content.setDesc(aData[2]);
            content.setPrice(aData[3]);
            content.setPhoto(aData[4]);
            content.setFlag(aData[5]);

            list.add(content);
        }
        return list;
    }
}
