package app.controllers;

import app.models.MainModel;
import app.views.ViewSkill;
import javafx.event.ActionEvent;

public class RankController implements ViewSkill {
    private MainModel mainModel;

    @Override
    public void setMainModel(MainModel model) {
        this.mainModel = model;
    }


    public void onClickMenuButton(ActionEvent actionEvent) {
        mainModel.setState(MainModel.MainState.MENU);
        mainModel.notifyViews();
    }
}
