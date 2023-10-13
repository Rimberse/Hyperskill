package carsharing;

import carsharing.model.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final String[] mainMenuOptions = {"Exit", "Log in as a manager", "Log in as a customer", "Create a customer"};
    private static final String[] managerMenuOptions = {"Back", "Company list", "Create a company"};
    private static final String[] companyMenuOptions = {"Back", "Car list", "Create a car"};
    private static final String[] customerMenuOptions = {"Back", "Rent a car", "Return a rented car", "My rented car"};
    private static final String[] errorMessages = {"The company list is empty!", "The car list is empty!", "The customer list is empty!", "You didn't rent a car!", "You've already rented a car!", "No available cars in the "};
    private static final String[] infoMessages = {"The company was created!", " company:", " cars:", "The car was added!", "The customer was added!", "Your rented car:", "Company:", "You've returned a rented car!", "You rented '"};
    private static final String[] userPrompts = {"Enter the company name:", "Enter the car name:", "Choose a company:", "Enter the customer name:", "Choose a customer:", "Choose a car:"};

    public static void main(String[] args) {
        String dbName = null;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-databaseFileName")) {
                dbName = args[i + 1];
                break;
            }
        }

        Scanner scanner = new Scanner(System.in);
        CompanyDao companyDao = new CompanyDao(dbName);
        CarDao carDao = new CarDao(dbName);
        CustomerDao customerDao = new CustomerDao(dbName);
        boolean isInManagerMenu = false;
        boolean isInCustomerMenu = false;
        Customer customer = null;

        while (true) {
            List<Company> companies = companyDao.getAll();
            List<Customer> customers = customerDao.getAll();
            if (!isInManagerMenu && !isInCustomerMenu) {
                for (int i = 1; i < mainMenuOptions.length; i++)
                    System.out.println(i + ". " + mainMenuOptions[i]);

                System.out.println(0 + ". " + mainMenuOptions[0]);
            } else if (isInManagerMenu) {
                for (int i = 1; i < managerMenuOptions.length; i++)
                    System.out.println(i + ". " + managerMenuOptions[i]);

                System.out.println(0 + ". " + managerMenuOptions[0]);
            } else if (isInCustomerMenu) {
                for (int i = 1; i < customerMenuOptions.length; i++)
                    System.out.println(i + ". " + customerMenuOptions[i]);

                System.out.println(0 + ". " + customerMenuOptions[0]);
            }

            int selectedOption = scanner.nextInt();
            scanner.nextLine();

            if (!isInManagerMenu && !isInCustomerMenu && selectedOption == 1) {
                isInManagerMenu = true;
                isInCustomerMenu = false;
                continue;
            } else if (!isInManagerMenu && !isInCustomerMenu && selectedOption == 2) {
                isInManagerMenu = false;
                isInCustomerMenu = true;

                if (customers.isEmpty()) {
                    isInCustomerMenu = false;
                    System.out.println(errorMessages[2]);
                } else {
                    System.out.println(userPrompts[4]);

                    for (int i = 0; i < customers.size(); i++)
                        System.out.println((i + 1) + ". " + customers.get(i).getName());

                    System.out.println(0 + ". " + customerMenuOptions[0]);

                    int option = scanner.nextInt();
                    scanner.nextLine();
                    customer = customers.get(option - 1);
                }

                continue;
            } else if (!isInManagerMenu && !isInCustomerMenu && selectedOption == 3) {
                isInManagerMenu = false;
                System.out.println(userPrompts[3]);
                String customerName = scanner.nextLine();
                Customer newCustomer = new Customer(customerDao.getAll().size() + 1, customerName);
                customerDao.save(newCustomer);
                System.out.println(infoMessages[4]);
            } else if (isInManagerMenu && selectedOption == 0)
                isInManagerMenu = false;
            else if (isInCustomerMenu && selectedOption == 0)
                isInCustomerMenu = false;
            else if (!isInManagerMenu && selectedOption == 0) {
                companyDao.destroy();
                carDao.destroy();
                customerDao.destroy();
                System.exit(0);
            }

            if (isInManagerMenu && selectedOption == 1) {
                if (companies.isEmpty())
                    System.out.println(errorMessages[0]);
                else {
                    System.out.println(userPrompts[2]);

                    for (int i = 0; i < companies.size(); i++)
                        System.out.println((i + 1) + ". " + companies.get(i).getName());

                    System.out.println(0 + ". " + managerMenuOptions[0]);

                    int option = scanner.nextInt();
                    scanner.nextLine();

                    if (option != 0) {
                        Company company = companies.get(option - 1);
                        boolean isInCompanyMenu = true;

                        while (isInCompanyMenu) {
                            System.out.println(company.getName() + infoMessages[1]);

                            for (int i = 1; i < companyMenuOptions.length; i++)
                                System.out.println(i + ". " + companyMenuOptions[i]);

                            System.out.println(0 + ". " + companyMenuOptions[0]);

                            option = scanner.nextInt();
                            scanner.nextLine();
                            List<Car> cars = carDao.getBy(company.getId());

                            if (option == 1) {
                                System.out.println(company.getName() + infoMessages[2]);

                                if (cars.isEmpty())
                                    System.out.println(errorMessages[1]);
                                else {
                                    for (int i = 0; i < cars.size(); i++)
                                        System.out.println((i + 1) + ". " + cars.get(i).getName());
                                }
                            } else if (option == 2) {
                                System.out.println(userPrompts[1]);
                                String carName = scanner.nextLine();
                                Car car = new Car(cars.size() + 1, carName, company.getId());
                                carDao.save(car);
                                System.out.println(infoMessages[3]);
                            } else
                                isInCompanyMenu = false;
                        }
                    }
                }
            } else if (isInManagerMenu && selectedOption == 2) {
                System.out.println(userPrompts[0]);
                String companyName = scanner.nextLine();

                Company company = new Company(companyDao.getAll().size() + 1, companyName);
                companyDao.save(company);
                System.out.println(infoMessages[0]);
            } else if (isInCustomerMenu && selectedOption == 1) {
                if (carDao.get(customer.getRentedCarId()).isPresent())
                    System.out.println(errorMessages[4]);
                else if (companies.isEmpty())
                    System.out.println(errorMessages[0]);
                else {
                    System.out.println(userPrompts[2]);

                    for (int i = 0; i < companies.size(); i++)
                        System.out.println((i + 1) + ". " + companies.get(i).getName());

                    System.out.println(0 + ". " + customerMenuOptions[0]);

                    int option = scanner.nextInt();
                    scanner.nextLine();

                    Company company = companies.get(option - 1);
                    List<Car> cars = carDao.getNonRentedBy(company.getId());
                    System.out.println(cars);

                    if (cars.isEmpty()) {
                        System.out.println(errorMessages[5] + company.getName() + " company");
                    } else {
                        System.out.println(userPrompts[5]);

                        for (int i = 0; i < cars.size(); i++)
                            System.out.println((i + 1) + ". " + cars.get(i).getName());

                        System.out.println(0 + ". " + customerMenuOptions[0]);
                        option = scanner.nextInt();
                        scanner.nextLine();

                        if (option != 0) {
                            Car car = cars.get(option - 1);
                            customer.setRentedCarId(car.getId());
                            customerDao.updateRentedCarId(customer);
                            System.out.println(infoMessages[8] + car.getName() + "'");
                        }
                    }
                }
            } else if (isInCustomerMenu && selectedOption == 2) {
                if (customer != null) {
                    Optional<Car> carOpt = carDao.get(customer.getRentedCarId());

                    if (carOpt.isEmpty())
                        System.out.println(errorMessages[3]);
                    else {
                        customer.setRentedCarId(-1);
                        customerDao.updateRentedCarId(customer);
                        System.out.println(infoMessages[7]);
                    }
                }
            } else if (isInCustomerMenu && selectedOption == 3) {
                if (customer != null) {
                    Optional<Car> carOpt = carDao.get(customer.getRentedCarId());
                    Optional<Company> companyOpt = null;

                    if (carOpt.isPresent())
                        companyOpt = companyDao.get(carOpt.get().getCompanyId());

                    if (carOpt.isEmpty() || companyOpt.isEmpty())
                        System.out.println(errorMessages[3]);
                    else if (carOpt.isPresent() && companyOpt.isPresent()) {
                        System.out.println(infoMessages[5]);
                        System.out.println(carOpt.get().getName());
                        System.out.println(infoMessages[6]);
                        System.out.println(companyOpt.get().getName());
                    }
                }
            }
        }
    }
}
