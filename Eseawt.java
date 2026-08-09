import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Eseawt extends Frame implements ActionListener, Printable {

    // Input fields
    private TextField cidField;
    private TextField nameField;
    private TextField addressField;
    private TextField prevField;
    private TextField currField;

    // Buttons
    private Button generateBtn;
    private Button printBtn;

    // Bill output
    private TextArea billArea;

    // Amount formatting
    private final DecimalFormat df = new DecimalFormat("#0.00");

    public Eseawt() {

        super("Electricity Billing System (AWT)");

        setSize(520, 520);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        Font monoFont = new Font("Monospaced", Font.PLAIN, 13);

        // -------------------------
        // Customer ID
        // -------------------------
        Label customerIdLabel = new Label("Customer ID");
        customerIdLabel.setBounds(20, 40, 120, 24);
        add(customerIdLabel);

        cidField = new TextField();
        cidField.setBounds(150, 40, 340, 24);
        add(cidField);

        // -------------------------
        // Customer Name
        // -------------------------
        Label nameLabel = new Label("Name");
        nameLabel.setBounds(20, 80, 120, 24);
        add(nameLabel);

        nameField = new TextField();
        nameField.setBounds(150, 80, 340, 24);
        add(nameField);

        // -------------------------
        // Address
        // -------------------------
        Label addressLabel = new Label("Address");
        addressLabel.setBounds(20, 120, 120, 24);
        add(addressLabel);

        addressField = new TextField();
        addressField.setBounds(150, 120, 340, 24);
        add(addressField);

        // -------------------------
        // Previous Reading
        // -------------------------
        Label previousLabel =
                new Label("Previous Reading");

        previousLabel.setBounds(20, 160, 120, 24);
        add(previousLabel);

        prevField = new TextField();
        prevField.setBounds(150, 160, 340, 24);
        add(prevField);

        // -------------------------
        // Current Reading
        // -------------------------
        Label currentLabel =
                new Label("Current Reading");

        currentLabel.setBounds(20, 200, 120, 24);
        add(currentLabel);

        currField = new TextField();
        currField.setBounds(150, 200, 340, 24);
        add(currField);

        // -------------------------
        // Generate Bill Button
        // -------------------------
        generateBtn = new Button("Generate Bill");
        generateBtn.setBounds(20, 240, 230, 36);
        generateBtn.addActionListener(this);
        add(generateBtn);

        // -------------------------
        // Print Bill Button
        // -------------------------
        printBtn = new Button("Print Bill");
        printBtn.setBounds(260, 240, 230, 36);
        printBtn.addActionListener(this);
        add(printBtn);

        // -------------------------
        // Bill Output Area
        // -------------------------
        billArea = new TextArea(
                "",
                10,
                60,
                TextArea.SCROLLBARS_VERTICAL_ONLY
        );

        billArea.setBounds(20, 290, 470, 180);
        billArea.setFont(monoFont);
        billArea.setEditable(false);
        add(billArea);

        // -------------------------
        // Window Closing
        // -------------------------
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        setVisible(true);
    }

    // -------------------------
    // Main Method
    // -------------------------
    public static void main(String[] args) {
        new Eseawt();
    }

    // -------------------------
    // Button Event Handling
    // -------------------------
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == generateBtn) {
            generateBill();
        }

        else if (e.getSource() == printBtn) {
            printBill();
        }
    }

    // -------------------------
    // Generate Bill
    // -------------------------
    private void generateBill() {

        String customerId = cidField.getText().trim();
        String customerName = nameField.getText().trim();
        String address = addressField.getText().trim();

        if (customerId.isEmpty()
                || customerName.isEmpty()
                || address.isEmpty()) {

            billArea.setText(
                    "Please enter Customer ID, Name and Address."
            );

            return;
        }

        int previousReading;
        int currentReading;

        // -------------------------
        // Read Previous Reading
        // -------------------------
        try {

            String previousText =
                    prevField.getText().trim();

            if (previousText.isEmpty()) {
                previousReading = 0;
            }

            else {
                previousReading =
                        Integer.parseInt(previousText);
            }

        }

        catch (NumberFormatException ex) {

            billArea.setText(
                    "Invalid Previous Reading!"
            );

            return;
        }

        // -------------------------
        // Read Current Reading
        // -------------------------
        try {

            String currentText =
                    currField.getText().trim();

            if (currentText.isEmpty()) {

                billArea.setText(
                        "Please enter Current Reading!"
                );

                return;
            }

            currentReading =
                    Integer.parseInt(currentText);
        }

        catch (NumberFormatException ex) {

            billArea.setText(
                    "Invalid Current Reading!"
            );

            return;
        }

        // -------------------------
        // Validate Readings
        // -------------------------
        if (previousReading < 0
                || currentReading < 0) {

            billArea.setText(
                    "Meter readings cannot be negative!"
            );

            return;
        }

        if (currentReading < previousReading) {

            billArea.setText(
                    "Current Reading must be greater than "
                    + "or equal to Previous Reading!"
            );

            return;
        }

        // -------------------------
        // Free Units
        // -------------------------
        final int FREE_UNITS = 100;

        int consumedUnits =
                currentReading - previousReading;

        int chargeableUnits =
                Math.max(0, consumedUnits - FREE_UNITS);

        // -------------------------
        // Current Bill
        // -------------------------
        double totalAmount =
                calculateBill(chargeableUnits);

        // -------------------------
        // Next Month Prediction
        // -------------------------
        int predictedUnits = consumedUnits;

        int nextMonthReading =
                currentReading + predictedUnits;

        int predictedChargeableUnits =
                Math.max(
                        0,
                        predictedUnits - FREE_UNITS
                );

        double predictedAmount =
                calculateBill(predictedChargeableUnits);

        // -------------------------
        // Date and Time
        // -------------------------
        String dateTime =
                LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern(
                                "dd-MM-yyyy HH:mm:ss"
                        )
                );

        // -------------------------
        // Generate Bill Text
        // -------------------------
        String bill =
                "================================\n"
                + "       ELECTRICITY BILL\n"
                + "================================\n"
                + "Customer ID      : "
                + customerId + "\n"
                + "Name             : "
                + customerName + "\n"
                + "Address          : "
                + address + "\n"
                + "Date / Time      : "
                + dateTime + "\n"
                + "--------------------------------\n"
                + "Previous Reading : "
                + previousReading + "\n"
                + "Current Reading  : "
                + currentReading + "\n"
                + "Consumed Units   : "
                + consumedUnits + "\n"
                + "Free Units       : "
                + FREE_UNITS + "\n"
                + "Chargeable Units : "
                + chargeableUnits + "\n"
                + "Total Amount     : Rs. "
                + df.format(totalAmount) + "\n"
                + "--------------------------------\n"
                + "NEXT MONTH PREDICTION\n"
                + "--------------------------------\n"
                + "Predicted Units  : "
                + predictedUnits + "\n"
                + "Next Reading     : "
                + nextMonthReading + "\n"
                + "Predicted Bill   : Rs. "
                + df.format(predictedAmount) + "\n"
                + "================================\n";

        billArea.setText(bill);
    }

    // -------------------------
    // Slab-Based Bill Calculation
    // -------------------------
    private double calculateBill(int units) {

        double amount = 0.0;
        int remainingUnits = units;
        int unitsInSlab;

        // First 100 units
        unitsInSlab = Math.min(
                remainingUnits,
                100
        );

        amount += unitsInSlab * 4.95;
        remainingUnits -= unitsInSlab;

        if (remainingUnits <= 0) {
            return amount;
        }

        // Next 50 units
        unitsInSlab = Math.min(
                remainingUnits,
                50
        );

        amount += unitsInSlab * 6.65;
        remainingUnits -= unitsInSlab;

        if (remainingUnits <= 0) {
            return amount;
        }

        // Next 50 units
        unitsInSlab = Math.min(
                remainingUnits,
                50
        );

        amount += unitsInSlab * 8.80;
        remainingUnits -= unitsInSlab;

        if (remainingUnits <= 0) {
            return amount;
        }

        // Next 100 units
        unitsInSlab = Math.min(
                remainingUnits,
                100
        );

        amount += unitsInSlab * 9.95;
        remainingUnits -= unitsInSlab;

        if (remainingUnits <= 0) {
            return amount;
        }

        // Next 100 units
        unitsInSlab = Math.min(
                remainingUnits,
                100
        );

        amount += unitsInSlab * 6.30;
        remainingUnits -= unitsInSlab;

        if (remainingUnits <= 0) {
            return amount;
        }

        // Next 100 units
        unitsInSlab = Math.min(
                remainingUnits,
                100
        );

        amount += unitsInSlab * 8.40;
        remainingUnits -= unitsInSlab;

        if (remainingUnits <= 0) {
            return amount;
        }

        // Next 200 units
        unitsInSlab = Math.min(
                remainingUnits,
                200
        );

        amount += unitsInSlab * 9.45;
        remainingUnits -= unitsInSlab;

        if (remainingUnits <= 0) {
            return amount;
        }

        // Next 200 units
        unitsInSlab = Math.min(
                remainingUnits,
                200
        );

        amount += unitsInSlab * 10.50;
        remainingUnits -= unitsInSlab;

        if (remainingUnits <= 0) {
            return amount;
        }

        // Remaining units
        amount += remainingUnits * 11.55;

        return amount;
    }

    // -------------------------
    // Print Bill
    // -------------------------
    private void printBill() {

        if (billArea.getText().trim().isEmpty()) {

            billArea.setText(
                    "Please generate the bill before printing."
            );

            return;
        }

        try {

            PrinterJob printerJob =
                    PrinterJob.getPrinterJob();

            printerJob.setPrintable(this);

            if (printerJob.printDialog()) {
                printerJob.print();
            }

        }

        catch (PrinterException ex) {

            billArea.setText(
                    "Printing Error: "
                    + ex.getMessage()
            );
        }
    }

    // -------------------------
    // Printable Interface
    // -------------------------
    @Override
    public int print(
            Graphics graphics,
            PageFormat pageFormat,
            int pageIndex
    ) throws PrinterException {

        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2 =
                (Graphics2D) graphics;

        g2.translate(
                pageFormat.getImageableX(),
                pageFormat.getImageableY()
        );

        g2.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        10
                )
        );

        String[] lines =
                billArea.getText().split("\n");

        int y = 15;

        for (String line : lines) {

            g2.drawString(line, 0, y);
            y += 15;
        }

        return PAGE_EXISTS;
    }
}
