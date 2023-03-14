package com.ldts23.l08gr04.state;

import com.ldts23.l08gr04.controller.Controller;
import com.ldts23.l08gr04.controller.game.EORController;
import com.ldts23.l08gr04.model.game.board.Board;
import com.ldts23.l08gr04.viewer.Viewer;
import com.ldts23.l08gr04.viewer.game.BoardViewer;

public class EORState extends State<Board>{

    public EORState(Board board) {
        super(board);
    }

    @Override
    protected Viewer<Board> getViewer() {
        return new BoardViewer(getModel());
    }

    @Override
    protected Controller<Board> getController() {
        return new EORController(getModel());
    }
}
