/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.jp3.cars_rental;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Mahmoud_Abusaqer
 */
public class rental_carController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button chooes_Date_Button;
    @FXML
    private Button available_Cars_Button;
    @FXML
    private Button car_Information_Button;
    @FXML
    private Button booking_Button;
    @FXML
    private Pane chooes_date_pane;
    @FXML
    private DatePicker pickUp_date;
    @FXML
    private DatePicker return_date;
    @FXML
    private Button search_Cars_Button;
    @FXML
    private Pane available_cars_pane;
    @FXML
    private TableView<AvailableCars> available_Cars_Table;
    @FXML
    private TableColumn<AvailableCars, Integer> tc_Number;
    @FXML
    private TableColumn<AvailableCars, Integer> tc_Car_Id;
    @FXML
    private Button Go_back_to_change_the_Date_Button;
    @FXML
    private Button Go_to_Car_Informaion_Button;
    @FXML
    private Pane car_information_pane;
    @FXML
    private Label car_id;
    @FXML
    private Label model;
    @FXML
    private Label brand;
    @FXML
    private Label num_of_doors;
    @FXML
    private Label num_of_seats;
    @FXML
    private Label speed;
    @FXML
    private Label color;
    @FXML
    private Label price_per_day;
    @FXML
    private Button Go_to_Booking_Button;
    @FXML
    private Button Go_back_to_change_the_car_Button;
    @FXML
    private Pane booking_pane;
    @FXML
    private TextField customer_Id;
    @FXML
    private TextField first_Name;
    @FXML
    private TextField last_Name;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton femal;
    @FXML
    private TextField address;
    @FXML
    private TextField phone;
    @FXML
    private TextField total_Rent_Days;
    @FXML
    private Label total_Price;
    @FXML
    private Button go_Back_Car_Information_Button;
    @FXML
    private Button Booking_Button;
    @FXML
    private TableView<Booking> view_Bookings_Table;
    @FXML
    private TableColumn<Booking, Integer> tc_VB_Id;
    @FXML
    private TableColumn<Booking, Integer> tc_VB_customerId;
    @FXML
    private TableColumn<Booking, Integer> tc_VB_carId;
    @FXML
    private TableColumn<Booking, Date> tc_VB_pickupDate;
    @FXML
    private TableColumn<Booking, Date> tc_VB_returnDate;
    @FXML
    private TableColumn<Booking, Double> tc_VB_totalDaysRent;
    @FXML
    private TableColumn<Booking, Double> tc_VB_totalPrice;
    @FXML
    private TableColumn<Booking, Integer> tc_VB_employeeId;
    @FXML
    private Button view_Bookings_Button;
    @FXML
    private Pane view_Bookings_Pane;

    Statement statement;
    List<Integer> availableCarsList = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection
                    = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cars_rental?serverTimezone=UTC",
                            "root", "");
            this.statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        //Available Cars
        tc_Number.setCellValueFactory(new PropertyValueFactory("id"));
        tc_Car_Id.setCellValueFactory(new PropertyValueFactory("carId"));
        //View Bookings
        tc_VB_Id.setCellValueFactory(new PropertyValueFactory("id"));
        tc_VB_customerId.setCellValueFactory(new PropertyValueFactory("customerId"));
        tc_VB_carId.setCellValueFactory(new PropertyValueFactory("carId"));
        tc_VB_pickupDate.setCellValueFactory(new PropertyValueFactory("pickupDate"));
        tc_VB_returnDate.setCellValueFactory(new PropertyValueFactory("returnDate"));
        tc_VB_totalDaysRent.setCellValueFactory(new PropertyValueFactory("totalDaysRent"));
        tc_VB_totalPrice.setCellValueFactory(new PropertyValueFactory("totalPrice"));
        tc_VB_employeeId.setCellValueFactory(new PropertyValueFactory("employeeId"));
        chooes_date_pane.toFront();
        available_cars_pane.setVisible(false);
        car_information_pane.setVisible(false);
        booking_pane.setVisible(false);
        view_Bookings_Pane.setVisible(false);
//        available_Cars_Table.getSelectionModel().selectedItemProperty().addListener(listener -> selectAvailableCars());
        try {
            if (rootPane.isVisible()) {
                showAvailableCar();
                pickUp_date.setValue(LocalDate.now());
            }
        } catch (SQLException ex) {
            Logger.getLogger(rental_carController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ButtonHandle(ActionEvent event) throws SQLException {
        if (event.getSource() == chooes_Date_Button) {
            chooes_date_pane.setVisible(true);
            chooes_date_pane.toFront();
            available_cars_pane.setVisible(false);
            car_information_pane.setVisible(false);
            booking_pane.setVisible(false);
            view_Bookings_Pane.setVisible(false);
//            chooes_Date_Button.setStyle("-fx-background-color: #c4dff6");
        } else if (event.getSource() == available_Cars_Button) {
            available_cars_pane.setVisible(true);
            chooes_date_pane.setVisible(false);
            available_cars_pane.toFront();
            car_information_pane.setVisible(false);
            booking_pane.setVisible(false);
            view_Bookings_Pane.setVisible(false);
        } else if (event.getSource() == car_Information_Button) {
            car_information_pane.setVisible(true);
            chooes_date_pane.setVisible(false);
            available_cars_pane.setVisible(false);
            car_information_pane.toFront();
            booking_pane.setVisible(false);
            view_Bookings_Pane.setVisible(false);
        } else if (event.getSource() == booking_Button) {
            booking_pane.setVisible(true);
            chooes_date_pane.setVisible(false);
            available_cars_pane.setVisible(false);
            car_information_pane.setVisible(false);
            view_Bookings_Pane.setVisible(false);
            booking_pane.toFront();
        } else if (event.getSource() == view_Bookings_Button) {
            view_Bookings_Pane.setVisible(true);
            booking_pane.setVisible(false);
            chooes_date_pane.setVisible(false);
            available_cars_pane.setVisible(false);
            car_information_pane.setVisible(false);
            view_Bookings_Pane.toFront();
            showAvailableCars();
        }
    }

    @FXML
    private void search_Cars_Button_Handle(ActionEvent event) throws SQLException {
        checkBeforeGoToAVCar();

    }

    @FXML
    private void Go_back_to_change_the_Date_Button_Handle(ActionEvent event) {
        chooes_date_pane.setVisible(true);
        chooes_date_pane.toFront();
        available_cars_pane.setVisible(false);
        car_information_pane.setVisible(false);
        booking_pane.setVisible(false);
    }

    @FXML
    private void Go_to_Car_Informaion_Button_Handle(ActionEvent event) throws SQLException {
        selectAvailableCars();
    }

    @FXML
    private void Go_back_to_change_the_Car_Button_Handle(ActionEvent event) {
        available_cars_pane.setVisible(true);
        chooes_date_pane.setVisible(false);
        available_cars_pane.toFront();
        car_information_pane.setVisible(false);
        booking_pane.setVisible(false);
    }

    @FXML
    private void Go_to_Booking_Button_Handle(ActionEvent event) throws SQLException {
        booking_pane.setVisible(true);
        chooes_date_pane.setVisible(false);
        available_cars_pane.setVisible(false);
        car_information_pane.setVisible(false);
        booking_pane.toFront();
        Integer totalRentDays2 = Integer.parseInt(total_Rent_Days.getText());
        Double totalPrice2 = totalRentDays2 * Double.parseDouble(price_per_day.getText());
        total_Price.setText(String.valueOf(totalPrice2));

    }

    @FXML
    private void go_Back_Car_Information_Button_Handle(ActionEvent event) {
        car_information_pane.setVisible(true);
        chooes_date_pane.setVisible(false);
        available_cars_pane.setVisible(false);
        car_information_pane.toFront();
        booking_pane.setVisible(false);
    }

    @FXML
    private void booking_Button_Handle(ActionEvent event) throws SQLException {
        addCustomer_Booking();
    }

    private void showAvailableCar() throws SQLException {
        ResultSet resultSet = this.statement.executeQuery("select * from available_cars");
        available_Cars_Table.getItems().clear();
        while (resultSet.next()) {
            AvailableCars availableCars = new AvailableCars();
            availableCars.setId(resultSet.getInt("id"));
            availableCars.setCarId(resultSet.getInt("car_id"));
            availableCarsList.add(availableCars.getCarId());
            available_Cars_Table.getItems().add(availableCars);
        }
        resultSet.close();
    }

//    private void showCarInformation() throws SQLException {
//        ResultSet resultSet = this.statement.executeQuery("select * from Car where id = 111");
//        while (resultSet.next()) {
//            Car car = new Car();
//            car.setId(resultSet.getInt("id"));
//            car_id.setText(String.valueOf(car.getId()));
//            car.setModel(resultSet.getString("model"));
//            model.setText(car.getModel());
//            car.setBrand(resultSet.getString("brand"));
//            brand.setText(car.getBrand());
//            car.setNumDoors(resultSet.getInt("num_doors"));
//            num_of_doors.setText(String.valueOf(car.getNumDoors()));
//            car.setNumSeats(resultSet.getInt("num_seats"));
//            num_of_seats.setText(String.valueOf(car.getNumSeats()));
//            car.setColor(resultSet.getString("color"));
//            color.setText(car.getColor());
//            car.setSpeed(resultSet.getInt("speed"));
//            speed.setText(String.valueOf(car.getSpeed()));
//            car.setPricePerDay(resultSet.getBigDecimal("price_per_day"));
//            price_per_day.setText(String.valueOf(car.getPricePerDay()));
//        }
//        resultSet.close();
//    }
    private void addCustomer_Booking() throws SQLException {
        if (customer_Id.getText().equals("") || first_Name.getText().equals("") || last_Name.getText().equals("")
                || (male.getText().equals("") && femal.getText().equals("")) || address.getText().equals("")
                || phone.getText().equals("") || total_Rent_Days.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("You can't book you should fill all the fields");
            alert.showAndWait();
        } else {
            //customer 
            Integer customerId = Integer.parseInt(customer_Id.getText());
            String firstName = first_Name.getText();
            String lastName = last_Name.getText();
            String customerAddress = address.getText();
            String customerPhone = phone.getText();
            String gender = null;
            if (male.getText() != null) {
                gender = male.getText();
            } else if (femal.getText() != null) {
                gender = femal.getText();
            }
            String addCustomerSql = "insert into customer values(" + customerId + ", '" + firstName + "', '" + lastName
                    + "', '" + customerAddress + "', '" + customerPhone + "', '" + gender + "')";
            int addCustomer = this.statement.executeUpdate(addCustomerSql);

            //booking 
            Integer carId = Integer.parseInt(car_id.getText());

            Integer totalRentDays = Integer.parseInt(total_Rent_Days.getText());
            Double totalPrice = Double.parseDouble(total_Price.getText());
            String addBookingSql = "insert into booking (customer_id, car_id, pickup_date, return_date, total_days_rent, total_price)"
                    + " values(" + customerId + ", " + carId + ", '" + pickUp_date.getValue()
                    + "', '" + return_date.getValue() + "', " + totalRentDays + ", " + totalPrice + ")";
            int addBooking = this.statement.executeUpdate(addBookingSql);

            if (addCustomer == 1 && addBooking == 1) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Car Booked");
                alert.setContentText("You can now take the car 😄");
                alert.showAndWait();
                clearFields();
                chooes_date_pane.setVisible(true);
                chooes_date_pane.toFront();
                available_cars_pane.setVisible(false);
                car_information_pane.setVisible(false);
                booking_pane.setVisible(false);
            }
        }
    }

    private void selectAvailableCars() throws SQLException {
        AvailableCars availableCars = available_Cars_Table.getSelectionModel().getSelectedItem();
        if (availableCars != null) {
            ResultSet resultSet = this.statement.executeQuery("select * from Car where id =" + availableCars.getCarId() + "");
            while (resultSet.next()) {
                Car car = new Car();
                car.setId(resultSet.getInt("id"));
                car_id.setText(String.valueOf(car.getId()));
                car.setModel(resultSet.getString("model"));
                model.setText(car.getModel());
                car.setBrand(resultSet.getString("brand"));
                brand.setText(car.getBrand());
                car.setNumDoors(resultSet.getInt("num_doors"));
                num_of_doors.setText(String.valueOf(car.getNumDoors()));
                car.setNumSeats(resultSet.getInt("num_seats"));
                num_of_seats.setText(String.valueOf(car.getNumSeats()));
                car.setColor(resultSet.getString("color"));
                color.setText(car.getColor());
                car.setSpeed(resultSet.getInt("speed"));
                speed.setText(String.valueOf(car.getSpeed()));
                car.setPricePerDay(resultSet.getBigDecimal("price_per_day"));
                price_per_day.setText(String.valueOf(car.getPricePerDay()));
            }
            resultSet.close();
            car_information_pane.setVisible(true);
            chooes_date_pane.setVisible(false);
            available_cars_pane.setVisible(false);
            car_information_pane.toFront();
            booking_pane.setVisible(false);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("You should select a car to view it's information");
            alert.showAndWait();
            available_cars_pane.setVisible(true);
            chooes_date_pane.setVisible(false);
            available_cars_pane.toFront();
            car_information_pane.setVisible(false);
            booking_pane.setVisible(false);
        }
    }

    private void checkBeforeGoToAVCar() throws SQLException {
        if (pickUp_date.getValue() == null || return_date.getValue() == null || total_Rent_Days.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("You should choose the date or fill the field to go next page");
            alert.showAndWait();
        } else {
            available_cars_pane.setVisible(true);
            chooes_date_pane.setVisible(false);
            available_cars_pane.toFront();
            car_information_pane.setVisible(false);
            booking_pane.setVisible(false);
            available_Cars_Table.getItems().clear();
            ResultSet resultSet = this.statement.executeQuery("select * from car");
            List<Car> list = new ArrayList<>();
            Car car = null;
            while (resultSet.next()) {
                car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setAvailable(resultSet.getString("available"));
                list.add(car);
            }
            for (int i = 0; i < list.size(); i++) {
                AvailableCars availableCars = new AvailableCars();
                if (list.get(i).getAvailable().equals("yes")) {
                    if (!availableCarsList.contains(list.get(i).getId())) {
                        Integer carId = list.get(i).getId();
                        String sql = "insert into available_cars (car_id) values(" + carId + ")";
                        this.statement.executeUpdate(sql);
                    }
                }
            }
            resultSet.close();
            showAvailableCar();
        }
    }

    private void clearFields() {
        pickUp_date.setValue(LocalDate.now());
        car_id.setText("");
        model.setText("");
        brand.setText("");
        num_of_doors.setText("");
        num_of_seats.setText("");
        speed.setText("");
        color.setText("");
        price_per_day.setText("");
        customer_Id.setText("");
        first_Name.setText("");
        last_Name.setText("");
        address.setText("");
        phone.setText("");
        total_Rent_Days.setText("");
        total_Price.setText("");
    }

    private void showAvailableCars() throws SQLException {
        ResultSet resultSet = this.statement.executeQuery("select * from booking");
        available_Cars_Table.getItems().clear();
        while (resultSet.next()) {
            Booking booking = new Booking();
            booking.setId(resultSet.getInt("id"));
            booking.setCustomerId(resultSet.getInt("customer_id"));
            booking.setCarId(resultSet.getInt("car_id"));
            booking.setPickupDate(resultSet.getDate("pickup_date"));
            booking.setReturnDate(resultSet.getDate("return_date"));
            booking.setTotalDaysRent(resultSet.getDouble("total_days_rent"));
            booking.setTotalPrice(resultSet.getDouble("total_price"));
            booking.setEmployeeId(resultSet.getInt("employee_id"));
            view_Bookings_Table.getItems().add(booking);
        }
        resultSet.close();
    }

}
