package com.example.lifcaremarvel.ui.home;

import com.example.lifcaremarvel.api.data.CharacterPOJO;

import java.util.List;

import androidx.annotation.NonNull;

public interface MainContract {

    interface View {

        void showProgress();

        void stopProgress(boolean hasMore);

        void showAttribution(String attribution);

        void showResult(@NonNull List<CharacterPOJO> entries);

        void showError(@NonNull Throwable e);

        void openCharacter(@NonNull android.view.View heroView, @NonNull CharacterPOJO character);
    }

    interface Actions {

        void initScreen();

        void loadCharacters(int offset);

        void characterClick(@NonNull android.view.View heroView, @NonNull CharacterPOJO character);

        void refresh();
    }
}
