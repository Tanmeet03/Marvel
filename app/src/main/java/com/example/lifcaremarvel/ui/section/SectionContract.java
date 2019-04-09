package com.example.lifcaremarvel.ui.section;

import com.example.lifcaremarvel.api.data.SectionPOJO;

import java.util.List;

public interface SectionContract {

    interface View {

        void showItems(List<SectionPOJO> entries, int position);

        void close();
    }

    interface Actions {

        void closeClick();
    }
}
