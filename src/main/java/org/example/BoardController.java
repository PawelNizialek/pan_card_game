package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class BoardController extends Game{
    public BoardController(){
    }
    @FXML
    private TableView<Card> computerCardTableView;
    @FXML
    private TableColumn<Card, String> computerCardValueTableColumn;
    @FXML
    private TableColumn<Card, String> computerCardColorTableColumn;
    @FXML
    private TableView<Card> playerCardTableView;
    @FXML
    private TableColumn<Card, String> playerCardValueTableColumn;
    @FXML
    private TableColumn<Card, String> playerCardColorTableColumn;
    @FXML
    private TableView<Card> pileCardTableView;
    @FXML
    private TableColumn<Card, String> pileCardValueTableColumn;
    @FXML
    private TableColumn<Card, String> pileCardColorTableColumn;

    @FXML
    private void throwHumanCard(MouseEvent mouseEvent){
        Card card = playerCardTableView.getSelectionModel().getSelectedItem();
        pile.addCard(List.of(card));
        human.throwCard(List.of(card));
        fillTableView();
    }
    @FXML
    void initialize(){
        Game game = new Game();
        playerCardValueTableColumn.setCellValueFactory(new PropertyValueFactory<Card, String>("value"));
        playerCardColorTableColumn.setCellValueFactory(new PropertyValueFactory<Card, String>("color"));
        computerCardValueTableColumn.setCellValueFactory(new PropertyValueFactory<Card, String>("value"));
        computerCardColorTableColumn.setCellValueFactory(new PropertyValueFactory<Card, String>("color"));
        pileCardValueTableColumn.setCellValueFactory(new PropertyValueFactory<Card, String>("value"));
        pileCardColorTableColumn.setCellValueFactory(new PropertyValueFactory<Card, String>("color"));
        playerCardTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        computerCardTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        pileCardTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        fillTableView();
    }
    private void fillTableView(){
        playerCardTableView.setItems(getPlayerCards(deck));
        computerCardTableView.setItems(getComputerCards(deck));
        pileCardTableView.setItems(getPileCards());
    }
    public ObservableList<Card> getComputerCards(Deck deck){
        ObservableList<Card> cards = FXCollections.observableArrayList();
        cards.addAll(computer.getCards());

        return cards;
    }
    public ObservableList<Card> getPlayerCards(Deck deck){
        ObservableList<Card> cards = FXCollections.observableArrayList();
        cards.addAll(human.getCards());
        return cards;
    }
    public ObservableList<Card> getPileCards(){
        ObservableList<Card> cards = FXCollections.observableArrayList();
        cards.addAll(pile.getPileCards());
        return cards;
    }


}
