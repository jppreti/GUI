package gui.javafx;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.regex.Pattern;

public class Formulario2 extends Application {

    BorderPane bdp;

    //Área Superior do BorderPane
    Label lblOption, lblKeyword;
    TextField txtKeyword;
    ChoiceBox chbOption;
    HBox pnlSearch;
    FadeTransition ft;

    //Área Central do BorderPane
    TabPane tbp;
    Tab tabSearch, tabEdit;
    //Tab Search Content
    TableView tbl;
    ContextMenu ctxMenu;
    MenuItem mnuEdit, mnuDelete;

    //Conteúdo da Aba de Edição
    GridPane gridEdit;
    Label lblName, lblQtty, lblPrice, lblType, lblQttyError;
    TextField txtName, txtQtty, txtPrice;
    ChoiceBox chbType;
    final FileChooser fc = new FileChooser();
    Button btnSelectPicture;
    final ImageView imgProduct = new ImageView();

    //Área Inferior do BorderPane
    HBox pnlBottom;
    Button btnNew, btnConfirm, btnEdit, btnCancel, btnDelete;

    @SuppressWarnings("unchecked")
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tab Form");
        bdp = new BorderPane();
        Scene scene = new Scene(bdp, 500, 400);

        //Construindo a área superior
        lblKeyword = new Label("Keyword:");
        lblKeyword.setOpacity(0);
        txtKeyword = new TextField();
        txtKeyword.setOpacity(0);
        txtKeyword.setTooltip(new Tooltip("Press ENTER to Search!"));
        txtKeyword.setPromptText("Type what to search here");
        txtKeyword.setPrefColumnCount(13);
        txtKeyword.setStyle(Style.BUTTON);
        txtKeyword.setOnKeyPressed(e -> { txtKeyword_onKeyPressed(e); });
        lblOption= new Label("Option:");
        lblOption.setOpacity(0);
        chbOption = new ChoiceBox();
        chbOption.setOpacity(0);
        chbOption.setOnAction(e -> { chbOption_onAction(e); });
        chbOption.getItems().addAll("Name", "Type");
        chbOption.getSelectionModel().selectFirst();

        pnlSearch = new HBox(5); //Espaço de 5 px
        pnlSearch.setAlignment(Pos.CENTER);
        pnlSearch.getChildren().addAll(lblKeyword, txtKeyword, lblOption, chbOption);
        pnlSearch.setPadding(new Insets(5));

        ft = new FadeTransition(Duration.millis(700));
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.setNode(lblKeyword);
        ft.setOnFinished(e->{ ft_onFinished(e); });

        //Construindo a área inferior
        btnNew = new Button("New");
        btnNew.setStyle(Style.BUTTON);
        btnNew.setOnAction(e -> { btnNew_onAction(e); });
        btnConfirm = new Button("Confirm");
        btnConfirm.setStyle(Style.BUTTON);
        btnConfirm.setOnAction(e -> { btnConfirm_onAction(e); });
        btnEdit = new Button("Edit");
        btnEdit.setTooltip(new Tooltip("Shortcut: CMD+E"));
        btnEdit.setStyle(Style.BUTTON);
        btnEdit.setOnAction(e -> { btnEdit_onAction(e); });
        btnCancel = new Button("Cancel");
        btnCancel.setStyle(Style.BUTTON);
        btnCancel.setOnAction(e -> { btnCancel_onAction(e); });
        btnDelete = new Button("Delete");
        btnDelete.setTooltip(new Tooltip("Shortcut: CMD+D"));
        btnDelete.setStyle(Style.BUTTON);
        btnDelete.setOnAction(e -> { btnDelete_onAction(e); });

        //Shortcut for Search
        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN),
                new Runnable() {
                    @Override public void run() {
                        txtKeyword.requestFocus();
                    }
                }
        );

        //Tecla de atalho para seleção da tabela
        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.T, KeyCombination.SHORTCUT_DOWN),
                new Runnable() {
                    @Override public void run() {
                        tbl.requestFocus();
                    }
                }
        );

        //Tecla de atalho para exclusão
        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.D, KeyCombination.SHORTCUT_DOWN),
                new Runnable() {
                    @Override public void run() {
                        btnDelete.fire();
                    }
                }
        );

        //Tecla de atalho para edição
        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.E, KeyCombination.SHORTCUT_DOWN),
                new Runnable() {
                    @Override public void run() {
                        btnEdit.fire();
                    }
                }
        );

        pnlBottom = new HBox(5);
        pnlBottom.setAlignment(Pos.CENTER);
        pnlBottom.getChildren().addAll(btnNew, btnConfirm, btnEdit, btnCancel, btnDelete);
        pnlBottom.setPadding(new Insets(5));

        //Construindo a Aba Busca
        final ObservableList produtos = FXCollections.observableArrayList(
                new Product("Wine",ProductType.BEVERAGE,new BigDecimal(40),10),
                new Product("Soap",ProductType.CLEAN,new BigDecimal(8),5),
                new Product("Cristal Clean",ProductType.CLEAN,new BigDecimal(5),25),
                new Product("Soda",ProductType.BEVERAGE,new BigDecimal(3),43)
        );

        TableColumn tbcName = new TableColumn<>("Name");
        tbcName.setCellValueFactory(new PropertyValueFactory("name"));

        tbcName.setCellFactory(TextFieldTableCell.forTableColumn());
        tbcName.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Product, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Product, String> t) {
                        ((Product) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setName(t.getNewValue());
                    }
                }
        );

        TableColumn tbcType = new TableColumn<>("Type");
        tbcType.setCellValueFactory(new PropertyValueFactory("type"));

        tbcType.setCellFactory(ChoiceBoxTableCell.forTableColumn(ProductType.values()));
        tbcType.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Product,ProductType>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Product, ProductType> t) {
                ((Product) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setType(t.getNewValue());
            }
        });

        TableColumn tbcPrice = new TableColumn<>("Price");
        tbcPrice.setCellValueFactory(new PropertyValueFactory("price"));

        TableColumn tbcQtty = new TableColumn<>("Qtty");
        tbcQtty.setCellValueFactory(new PropertyValueFactory("qtty"));

        TableColumn tbcImg = new TableColumn<>("Image");
        tbcImg.setCellValueFactory(new PropertyValueFactory("img"));

        tbl = new TableView();
        tbl.setTooltip(new Tooltip("Press the right button of the mouse for more options!"));
        tbl.setPrefHeight(300);
        tbl.setItems(produtos);

        tbl.getColumns().addAll(tbcName, tbcType, tbcPrice, tbcQtty, tbcImg);
        tbl.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tbl.setStyle("-fx-selection-bar: orange; -fx-selection-bar-non-focused: moccasin;");
        tbl.setOnMouseClicked(e -> { tbl_onMouseClicked(e); });
        tbl.setOnKeyPressed(e -> { tbl_onKeyPressed(e); });
        tbl.setEditable(true);

        ctxMenu = new ContextMenu();
        mnuEdit = new MenuItem("Edit");
        mnuEdit.setOnAction(e -> { mnuEdit_onAction(e); });
        mnuDelete = new MenuItem("Delete");
        mnuDelete.setOnAction(e -> { mnuDelete_onAction(e); });
        ctxMenu.getItems().addAll(mnuEdit, mnuDelete);
        tbl.setContextMenu(ctxMenu);

        //Building the Tab Edit for the Center Area
        lblName = new Label("Name:");
        lblName.setMinWidth(70);
        lblType = new Label("Type:");
        lblQtty = new Label("Qtty:");
        lblQttyError = new Label("");lblQttyError.setTextFill(Color.RED);
        lblQttyError.setStyle("-fx-font-size: 11px;");
        lblPrice = new Label("Price:");

        txtName = new TextField();
        txtName.setPromptText("Product description");
        txtName.setMinWidth(200);
        txtName.setPrefWidth(300);
        chbType = new ChoiceBox<>();
        for (ProductType pt : ProductType.values())
            chbType.getItems().add(pt);
        txtQtty = new TextField();
        txtQtty.setMinWidth(30);
        txtQtty.setPrefWidth(50);
        txtQtty.setMaxWidth(70);

        txtPrice = new TextField();
        txtPrice.setMinWidth(30);
        txtPrice.setPrefWidth(50);
        txtPrice.setMaxWidth(100);

        btnSelectPicture = new Button("Select Picture");
        btnSelectPicture.setOnAction(e -> {
            btnSelect_onPicture(e, primaryStage);
        });

        fc.setTitle("Select Product Image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );

        imgProduct.setFitWidth(100);
        imgProduct.setFitHeight(100);
        imgProduct.setPreserveRatio(true);

        gridEdit = new GridPane();

        gridEdit.setAlignment(Pos.CENTER);
        gridEdit.setHgap(10); gridEdit.setVgap(10);
        gridEdit.setPadding(new Insets(25, 25, 25, 25));

        gridEdit.add(lblName, 0, 0);
        gridEdit.add(txtName, 1, 0);
        gridEdit.add(lblType, 0, 1);
        gridEdit.add(chbType, 1, 1);
        gridEdit.add(lblQtty, 0, 2);
        gridEdit.add(txtQtty, 1, 2);
        gridEdit.add(lblQttyError, 2, 2);
        gridEdit.add(lblPrice, 0, 3);
        gridEdit.add(txtPrice, 1, 3);
        gridEdit.add(btnSelectPicture, 0, 4);
        gridEdit.add(imgProduct, 1, 4);

        //Juntando tudo
        tabSearch = new Tab("Products");
        tabSearch.setOnSelectionChanged(e -> {tabSearch_onSelectionChange(e); });
        tabSearch.setClosable(false);
        tabSearch.setContent(tbl);

        tabEdit = new Tab("Edit");
        tabEdit.setOnSelectionChanged(e -> {tabEdit_onSelectionChange(e); });
        tabEdit.setClosable(false);
        tabEdit.setContent(gridEdit);

        tbp = new TabPane();
        tbp.getTabs().addAll(tabSearch, tabEdit);

        bdp.setTop(pnlSearch);
        bdp.setCenter(tbp);
        bdp.setBottom(pnlBottom);

        primaryStage.setScene(scene);
        primaryStage.show();
        tbl.requestFocus();
        setEditable(false);
        ft.play();
    }

    private void btnSelect_onPicture(ActionEvent e, Stage stage) {
        File f = fc.showOpenDialog(stage);
        if (f != null){
            imgProduct.setImage(new Image(f.toURI().toString()));
        }
    }

    private void tbl_onKeyPressed(KeyEvent e) {
        if (e.getCode()==KeyCode.ENTER)
            btnEdit.fire();
    }

    private void ft_onFinished(ActionEvent e) {
        if (ft.getNode()==lblKeyword) {
            ft.setNode(txtKeyword);
            ft.play();
        }else if (ft.getNode()==txtKeyword) {
            ft.setNode(lblOption);
            ft.play();
        }else if (ft.getNode()==lblOption) {
            ft.setNode(chbOption);
            ft.play();
        }else if(ft.getNode()==chbOption){
            txtKeyword.requestFocus();
        }
    }

    private void tabSearch_onSelectionChange(Event e) {
        if (tabSearch.isSelected() && btnNew.isDisabled()) {
            tbp.getSelectionModel().select(tabEdit);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Search not Allowed!");
            alert.setContentText("You are editing a register. You have to CONFIRM or CANCEL your operation.");
            alert.showAndWait();
        }
    }

    private void tabEdit_onSelectionChange(Event e) {
        if (tabEdit.isSelected()) {
            fillForm();
        }
    }

    private void tbl_onMouseClicked(MouseEvent e) {
        if (e.getClickCount()>=2) {
            tbp.getSelectionModel().select(1);
        }
    }

    private void mnuDelete_onAction(ActionEvent e) {
        btnDelete_onAction(e);
    }

    private void mnuEdit_onAction(ActionEvent e) {
        btnEdit_onAction(e);
    }

    private void chbOption_onAction(Event e) {
        if (tbp!=null) tbp.getSelectionModel().select(0);
    }

    private void txtKeyword_onKeyPressed(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            tbp.getSelectionModel().select(0);
        }
    }

    private void btnDelete_onAction(ActionEvent e) {
        Alert alert;
        Product p = (Product) tbl.getSelectionModel().getSelectedItem();
        if (p != null) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("DELETE Dialog");
            alert.setContentText("Are you sure you want to delete it?");

            Optional result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                tbl.getItems().remove(p);
                clearForm();
            }
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Product not selected!");
            alert.setContentText("You must select a product in the table in order to delete.");
            alert.showAndWait();
        }
    }

    private void btnCancel_onAction(ActionEvent e) {
        setEditable(false);
        clearForm();
        fillForm();
    }

    private void btnEdit_onAction(ActionEvent e) {
        Alert alert;
        Product p = (Product) tbl.getSelectionModel().getSelectedItem();
        if (p!=null) {
            setEditable(true);
            tbp.getSelectionModel().select(1);
            txtName.requestFocus();
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Product not selected!");
            alert.setContentText("You must select a product in the table in order to edit it.");
            alert.showAndWait();
        }
    }

    private void btnConfirm_onAction(ActionEvent e) {
        boolean error = false;
        if (!Pattern.matches("[0-9]*\\.?[0-9]*", txtQtty.getText())){
            txtQtty.setStyle(Style.ERROR);
            lblQttyError.setText("Value must be a number >= 0");
            error=true;
            txtQtty.requestFocus();
        }

        if (!error) {
            Product p = (Product) tbl.getSelectionModel().getSelectedItem();
            if (p == null) {
                p = new Product();
                tbl.getItems().add(p);
            }
            p.setName(txtName.getText());
            p.setPrice(new BigDecimal(txtPrice.getText()));
            p.setQtty(new Float(txtPrice.getText()));
            p.setType((ProductType) chbType.getSelectionModel().getSelectedItem());
            ImageView iv = new ImageView(imgProduct.getImage());
            iv.setFitWidth(100);
            iv.setFitHeight(100);
            iv.setPreserveRatio(true);
            p.setImg(iv);
            clearForm();
            setEditable(false);
            tbl.refresh();
            tbp.getSelectionModel().select(0);
            tbl.requestFocus();
            tbl.getSelectionModel().select(p);
        }
    }

    private void btnNew_onAction(ActionEvent e) {
        setEditable(true);
        tbp.getSelectionModel().select(1);
        clearForm();
        txtName.requestFocus();
        tbl.getSelectionModel().select(null);
    }

    public void setEditable(boolean edit) {
        btnNew.setDisable(edit);
        btnConfirm.setDisable(!edit);
        btnEdit.setDisable(edit);
        btnCancel.setDisable(!edit);
        btnDelete.setDisable(edit);
        gridEdit.setDisable(!edit);
    }

    public void clearForm() {
        txtName.setText("");
        txtQtty.setText("");
        txtPrice.setText("");
        chbType.getSelectionModel().select(-1);
        txtName.setStyle("");
        txtQtty.setStyle("");
        txtPrice.setStyle("");
        //imgProduct.setImage(null); it doesn't work properly
        lblQttyError.setText("");
    }

    public void fillForm() {
        Product p = (Product) tbl.getSelectionModel().getSelectedItem();
        if (p!=null) {
            txtName.setText(p.getName());
            txtQtty.setText(p.getQtty()+"");
            txtPrice.setText(p.getPrice().toString());
            chbType.getSelectionModel().select(p.getType());
            imgProduct.setImage(p.getImg().getImage());
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public static class Product {
        private String name;
        private ProductType type;
        private BigDecimal price;
        private float qtty;
        private ImageView img = new ImageView(new Image(getClass().getResourceAsStream("search.png")));

        public Product() {}
        public Product(String name, ProductType type, BigDecimal price, float qtty) {
            this.name = name;
            this.type = type;
            this.price = price;
            this.qtty = qtty;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ProductType getType() {
            return type;
        }

        public void setType(ProductType type) {
            this.type = type;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public float getQtty() {
            return qtty;
        }

        public void setQtty(float qtty) {
            this.qtty = qtty;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            result = prime * result + ((type == null) ? 0 : type.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Product other = (Product) obj;
            if (name == null) {
                if (other.name != null)
                    return false;
            } else if (!name.equals(other.name))
                return false;
            if (type != other.type)
                return false;
            return true;
        }

        public String toString() {
            return "Product: " + name;
        }
        public ImageView getImg() {
            return img;
        }
        public void setImg(ImageView img) {
            this.img = img;
        }
    }

    public static enum ProductType {
        BEVERAGE, CLEAN, FOOD
    }

    public static class Style {
        public static final String BUTTON = "-fx-background-color: linear-gradient(#ffd65b, #e68400), linear-gradient(#ffef84, #f2ba44), linear-gradient(#ffea6a, #efaa22), linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%), linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0)); -fx-background-radius: 10;   -fx-background-insets: 0,1,2,3,0;  -fx-text-fill: #654b00;     -fx-font-weight: bold;     -fx-font-size: 12px;     -fx-padding: 10 10 10 10;";
        public static final String ERROR = "-fx-background-color: red,linear-gradient(to bottom, derive(red,60%) 5%,derive(red,90%) 40%);";
    }
}