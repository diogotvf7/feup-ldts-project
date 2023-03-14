package com.ldts23.l08gr04.state;

import com.ldts23.l08gr04.controller.Controller;
import com.ldts23.l08gr04.controller.rules.RulesController;
import com.ldts23.l08gr04.model.rules.Rules;
import com.ldts23.l08gr04.viewer.Viewer;
import com.ldts23.l08gr04.viewer.rules.RulesViewer;

public class RulesState extends State<Rules> {

    public RulesState(Rules model) {
        super(model);
    }

    @Override
    protected Viewer<Rules> getViewer() {
        return new RulesViewer(getModel());
    }

    @Override
    protected Controller<Rules> getController() {
        return new RulesController(getModel());
    }
}
