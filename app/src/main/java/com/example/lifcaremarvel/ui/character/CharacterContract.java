package com.example.lifcaremarvel.ui.character;

import com.example.lifcaremarvel.api.data.CharacterPOJO;
import com.example.lifcaremarvel.api.data.SectionPOJO;

import java.util.List;

import androidx.annotation.NonNull;

public interface CharacterContract {

    interface View {

        void showAttribution(String attribution);

        void showCharacter(@NonNull CharacterPOJO character);

        void showComics(@NonNull List<SectionPOJO> entries);

        void showSeries(@NonNull List<SectionPOJO> entries);

        void showStories(@NonNull List<SectionPOJO> entries);

        void showEvents(@NonNull List<SectionPOJO> entries);

        void showError(@NonNull Throwable e);

        void openSection(@SectionPOJO.Type int type, @NonNull android.view.View heroView, String attribution, @NonNull List<SectionPOJO> entries, int position);

        void openLink(@NonNull String url);
    }

    interface Actions {

        void loadComics(long characterId, int offset);

        void loadSeries(long characterId, int offset);

        void loadStories(long characterId, int offset);

        void loadEvents(long characterId, int offset);

        void sectionClick(@SectionPOJO.Type int type, @NonNull android.view.View heroView, @NonNull List<SectionPOJO> entries, int position);

        void linkClick(@NonNull String url);
    }
}
