package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    void initialize(){
        Game game = new Game();
        playerCardValueTableColumn.setCellValueFactory(new PropertyValueFactory<Card, String>("value"));
        playerCardColorTableColumn.setCellValueFactory(new PropertyValueFactory<Card, String>("color"));
        computerCardValueTableColumn.setCellValueFactory(new PropertyValueFactory<Card, String>("value"));
        computerCardColorTableColumn.setCellValueFactory(new PropertyValueFactory<Card, String>("color"));
        playerCardTableView.setItems(getPlayerCards(deck));
        computerCardTableView.setItems(getComputerCards(deck));
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
    private void initTable(){

    }
    private void initCols(){

    }
    private void editableCols(){}

}
