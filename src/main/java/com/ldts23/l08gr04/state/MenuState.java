package com.ldts23.l08gr04.state;

import com.ldts23.l08gr04.controller.Controller;
import com.ldts23.l08gr04.controller.menu.MenuController;
import com.ldts23.l08gr04.model.menu.Menu;
import com.ldts23.l08gr04.viewer.Viewer;
import com.ldts23.l08gr04.viewer.menu.MenuViewer;

public class MenuState extends State<Menu> {

    public MenuState(Menu model) { super(model); }

    @Override
    protected Viewer<Menu> getViewer() { return new MenuViewer(getModel()); }

    @Override
    protected Controller<Menu> getController() { return new MenuController(getModel()); }
}
