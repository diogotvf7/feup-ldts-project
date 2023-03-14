package com.ldts23.l08gr04.viewer.game;

import com.ldts23.l08gr04.gui.GUI;
import com.ldts23.l08gr04.model.Position;
import com.ldts23.l08gr04.model.game.element.Card;
import com.ldts23.l08gr04.model.game.element.Dealer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DealerViewerTest {

    private static Dealer dealer;
    private static DealerViewer viewer;
    private static GUI gui;
    private Position dealerPos;
    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        dealer.clearHand();
        viewer = new DealerViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawNameTest() {
        viewer.drawDealer(dealer, gui);
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(gui.getWidth()/2 - 64, 16),"DEALER");
    }
    @Test
    void drawBothHandValuesTest() {
        dealer.addCard(new Card(14, 1));
        viewer.drawDealer(dealer,gui);
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(gui.getWidth()/2 -64, 48), dealer.getHandValues()[0] + "-" + dealer.getHandValues()[1]);
    }
    @Test
    void drawSingleHandValuesTest() {
            dealer.addCard(new Card(13, 1));
            viewer.drawDealer(dealer,gui);
            Mockito.verify(gui, Mockito.times(1)).drawText(new Position(gui.getWidth()/2 -64, 48), Integer.toString(dealer.getHandValues()[1]));
        }
    @Test
    void drawHandValuesOnStand() {
        dealer.addCard(new Card(14, 1));
        dealer.addCard(new Card(9, 1));
        viewer.drawDealer(dealer,gui);
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(gui.getWidth()/2 -64, 48), Integer.toString(dealer.getHandValues()[1]));
    }
    @Test
    void drawHandTest() {
        dealer.addCard(new Card(10, 1));
        dealer.addCard(new Card(11, 1));
        dealer.addCard(new Card(10, 1));
        viewer.drawDealer(dealer, gui);
        Mockito.verify(gui, Mockito.times(3)).drawImage(Mockito.any(Position.class), Mockito.anyString());
    }
    }


