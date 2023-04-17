package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import model.DatabaseConnector;
import model.Expense;

public class OrderExpensesController extends ProductBaseController {

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    public void goToCatalog(ActionEvent event) {
        ScreenController.goToCatalogPage(event);
    }

    @FXML
    protected void initialize() {
        List<Expense> expenses = fetchOrderHistory();
        populateBarChart(expenses);
    }

    private List<Expense> fetchOrderHistory() {
        List<Expense> expenses = new ArrayList<>();
        try {
        	Connection conn = DatabaseConnector.getInstance();
            PreparedStatement stmt = conn.prepareStatement(
                     "SELECT purchasedPrice, order_timestamp FROM user_order_history WHERE user_name = ?");
         
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                double price = rs.getDouble("purchasedPrice");
                String date = rs.getString("order_timestamp");
                Expense expense = new Expense(date, price);
                expenses.add(expense);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expenses;
    }

    private void populateBarChart(List<Expense> expenses) {
        List<String> dates = new ArrayList<>();
        for (Expense expense : expenses) {
            dates.add(expense.getDate());
        }
        Collections.sort(dates);
        List<Expense> expensesByDate = new ArrayList<>();
        for (String date : dates) {
            double total = 0;
            for (Expense expense : expenses) {
                if (expense.getDate().equals(date)) {
                    total += expense.getPrice();
                }
            }
            Expense expense = new Expense(date, total);
            expensesByDate.add(expense);
        }
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (Expense expense : expensesByDate) {
            series.getData().add(new XYChart.Data<>(expense.getDate(), expense.getPrice()));
        }
        barChart.getData().add(series);
        barChart.setLegendVisible(false);
    }

    @Override
    public void logOff() {
        super.logOff();
        barChart.getData().clear();
    }
}
