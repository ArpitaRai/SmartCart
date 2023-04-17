package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import model.DatabaseConnector;
import model.Product;

public class CatalogExpensesController extends ProductBaseController {

    @FXML
    PieChart catalogData;

    // Go to the Catalog Page
    @FXML
    void goToCatalog(ActionEvent event) {
        ScreenController.goToCatalogPage(event);
    }

    @Override
    public void initialize() {
        Map<String, Double> categoryShares = new HashMap<>();
        double totalShare = 0;

        try {
            Connection conn = DatabaseConnector.getInstance();
            Statement statement = conn.createStatement();
            String query = "select purchasedPrice, catalog from user_order_history where user_name = '" + userId + "'";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String category = rs.getString(2);
                double value = Double.parseDouble(rs.getString(1));
                categoryShares.put(category, categoryShares.getOrDefault(category, 0.0) + value);
                totalShare += value;
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Map.Entry<String, Double> entry : categoryShares.entrySet()) {
            String label = String.format("%s $%.2f", entry.getKey(), entry.getValue());
            double percentage = (entry.getValue() / totalShare) * 100;
            pieChartData.add(new PieChart.Data(label, percentage));
        }

        catalogData.setData(pieChartData);
        catalogData.setClockwise(true);
        catalogData.setLabelLineLength(2000);
        catalogData.setStartAngle(4000);
        catalogData.setLabelsVisible(true); // set labels visible
    }
}
