package library;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
	private static Admin admin;
	private static Librarian librarian;
	public static void main(String[] args) {
		admin = new Admin();
		librarian = new Librarian();
		showMainMenu();
		checkMainMenu();
	}

	private static void showMainMenu() {
		System.out.println("================Library Management================");
		System.out.println("|                                                |");
		System.out.println("|  Choose an option:                             |");
		System.out.println("|  1. Act as admin                               |");
		System.out.println("|  2. Act as librarian                           |");
		System.out.println("|  3. Quit                                       |");
		System.out.println("|                                                |");
		System.out.println("==================================================");
	}

	private static void checkMainMenu() {
		Scanner sc = new Scanner(System.in);
		int choice = Integer.parseInt(sc.nextLine());
		if (choice == 3) {
			System.exit(0);
		}
		while (choice != 1 && choice != 2) {
			showMainMenu();
			choice = Integer.parseInt(sc.nextLine());
		}
		if (choice == 1) {
			showAdminMenu();
			checkAdminMenu();
		} else {
			showLibrarianMenu();
			checkLibrarianMenu();
		}
	}

	private static void showAdminMenu() {
		System.out.println("================Library Management================");
		System.out.println("|                                                |");
		System.out.println("|  Choose an option:                             |");
		System.out.println("|                                                |");
		System.out.println("|         ------ Librarian Manager ------        |");
		System.out.println("|  1. Create a librarian account                 |");
		System.out.println("|  2. Remove a librarian account                 |");
		System.out.println("|  3. Update a librarian account                 |");
		System.out.println("|  4. Show all librarian accounts                |");
		System.out.println("|                                                |");
		System.out.println("|         ------  Student Manager  ------        |");
		System.out.println("|  5. Create a student account                   |");
		System.out.println("|  6. Remove a student account                   |");
		System.out.println("|  7. Update a student account                   |");
		System.out.println("|  8. Show all student accounts                  |");
		System.out.println("|                                                |");
		System.out.println("|  9. Back                                       |");
		System.out.println("|  10. Quit                                      |");
		System.out.println("|                                                |");
		System.out.println("==================================================");
	}

	private static void checkAdminMenu() {
		Scanner sc = new Scanner(System.in);
		int choice = Integer.parseInt(sc.nextLine());
		if (choice == 9) {
			showMainMenu();
			checkMainMenu();
		}
		if (choice == 10) {
			System.exit(0);
		}
		while (choice < 1 && choice > 8) {
			showAdminMenu();
			choice = Integer.parseInt(sc.nextLine());
		}
		if (choice == 1) {
			System.out.println("Please enter librarian details:");
			String newLibrarianID = admin.createLibrarian();
			if (newLibrarianID != null && !newLibrarianID.trim().isEmpty()) {
				showAdminMenu();
				checkAdminMenu();
			} else {
				System.err.println("Error! Can not create librarian account...");
				showAdminMenu();
				checkAdminMenu();
			}
		} else if (choice == 2) {
			System.out.println("Please enter librarian ID:");
			String librarianID = sc.nextLine();
			while (admin.getLibrarianByID(librarianID) == null) {
				System.err.println("Error! The librarian ID does not exist! \nPlease enter another ID: ");
				librarianID = sc.nextLine();
			}
			System.out.println("Deleting... Librarian ID " + librarianID);
			boolean isDeleted = admin.removeLibrarian(librarianID);
			if (isDeleted == true) {
				showAdminMenu();
				checkAdminMenu();
			} else {
				System.err.println("Error! Can not delete librarian account...");
				showAdminMenu();
				checkAdminMenu();
			}
		} else if (choice == 3) {
			System.out.println("Please enter librarian ID:");
			String librarianID = sc.nextLine();
			while (admin.getLibrarianByID(librarianID) == null) {
				System.err.println("Error! The librarian ID does not exist! \nPlease enter another ID: ");
				librarianID = sc.nextLine();
			}
			Librarian updatedLibrarianAccount = new Librarian();
			updatedLibrarianAccount.promtDetail();
			boolean isUpdated = admin.updateLibrarian(librarianID, updatedLibrarianAccount);
			if (isUpdated == true) {
				showAdminMenu();
				checkAdminMenu();
			} else {
				System.err.println("Error! Can not update librarian account...");
				showAdminMenu();
				checkAdminMenu();
			}
		} else if (choice == 4) {
			System.out.println("Loading librarian accounts...");
			ArrayList<Librarian> libList = admin.getAllLibrarian();
			for (Librarian lib : libList) {
				System.out.println(lib.toString());
			}
			System.out.println();
			showAdminMenu();
			checkAdminMenu();
		} else if (choice == 5) {
			System.out.println("Please enter student details:");
			String newStudentID = admin.createStudent();
			if (newStudentID != null && !newStudentID.trim().isEmpty()) {
				showAdminMenu();
				checkAdminMenu();
			} else {
				System.err.println("Error! Can not create student account...");
				showAdminMenu();
				checkAdminMenu();
			}
		} else if (choice == 6) {
			System.out.println("Please enter student ID:");
			String studentID = sc.nextLine();
			while (admin.getStudentByID(studentID) == null) {
				System.err.println("Error! The student ID does not exist! \nPlease enter another ID: ");
				studentID = sc.nextLine();
			}
			System.out.println("Deleting... Student ID " + studentID);
			boolean isDeleted = admin.removeStudent(studentID);
			if (isDeleted == true) {
				showAdminMenu();
				checkAdminMenu();
			} else {
				System.err.println("Error! Can not delete student account...");
				showAdminMenu();
				checkAdminMenu();
			}
		} else if (choice == 7) {
			System.out.println("Please enter student ID:");
			String studentID = sc.nextLine();
			while (admin.getStudentByID(studentID) == null) {
				System.err.println("Error! The student ID does not exist! \nPlease enter another ID: ");
				studentID = sc.nextLine();
			}
			Student updatedStudentAccount = new Student();
			updatedStudentAccount.promtDetail();
			updatedStudentAccount.promtEducationDetail();
			boolean isUpdated = admin.updateStudent(studentID, updatedStudentAccount);
			if (isUpdated == true) {
				showAdminMenu();
				checkAdminMenu();
			} else {
				System.err.println("Error! Can not update student account...");
				showAdminMenu();
				checkAdminMenu();
			}
		} else if (choice == 8) {
			System.out.println("Loading student accounts...");
			ArrayList<Student> stuList = admin.getAllStudent();
			for (Student stu : stuList) {
				System.out.println(stu.toString());
			}
			System.out.println();
			showAdminMenu();
			checkAdminMenu();
		}
	}

	private static void showLibrarianMenu() {
		System.out.println("================Library Management================");
		System.out.println("|                                                |");
		System.out.println("|  Choose an option:                             |");
		System.out.println("|                                                |");
		System.out.println("|         ------  Student Manager  ------        |");
		System.out.println("|  1. Show all student accounts                  |");
		System.out.println("|                                                |");
		System.out.println("|  2. Back                                       |");
		System.out.println("|  3. Quit                                       |");
		System.out.println("|                                                |");
		System.out.println("==================================================");
	}

	private static void checkLibrarianMenu() {
		Scanner sc = new Scanner(System.in);
		int choice = Integer.parseInt(sc.nextLine());
		if (choice == 2) {
			showMainMenu();
			checkMainMenu();
		}
		if (choice == 3) {
			System.exit(0);
		}
		while (choice < 1 && choice > 6) {
			showLibrarianMenu();
			choice = Integer.parseInt(sc.nextLine());
		}
		if (choice == 1) {
			System.out.println("Loading student accounts...");
			ArrayList<Student> stuList = librarian.getAllStudent();
			for (Student stu : stuList) {
				System.out.println(stu.toString());
			}
			System.out.println();
			showLibrarianMenu();
			checkLibrarianMenu();
		}
	}

}

class User {
	private String ID;
	private String name;
	private String email;
	private String password;
	private String birthday;
	private String phoneNumber;
	private String address;
	private String gender;
	private int status;
	private String createdAt;
	private String updatedAt;

	public User(String ID, String name, String email, String password, String birthday, String phoneNumber,
			String address, String gender) {
		this.ID = ID;
		this.name = name;
		this.email = email;
		this.password = password;
		this.birthday = birthday;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.gender = gender;
		this.status = 1;
		this.createdAt = getCurrentDatetime();
		this.updatedAt = getCurrentDatetime();
	}

	public User() {
		this.status = 1;
		this.createdAt = getCurrentDatetime();
		this.updatedAt = getCurrentDatetime();
	};

	public User(String ID) {
		this.ID = ID;
	};

	private String getCurrentDatetime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

	public String getName() {
		return name;
	}

	public boolean setName(String name) {
		if (isValidName(name)) {
			this.name = name;
			return true;
		} else {
			return false;
		}
	}

	public String getEmail() {
		return email;
	}

	public boolean setEmail(String email) {
		if (isValidEmail(email)) {
			this.email = email;
			return true;
		} else {
			return false;
		}
	}

	public String getPassword() {
		return password;
	}

	public boolean setPassword(String password) {
		if (isValidPassword(password)) {
			this.password = password;
			return true;
		} else {
			return false;
		}
	}

	public String getBirthday() {
		return birthday;
	}

	public boolean setBirthday(String birthday) {
		if (isValidBirthday(birthday)) {
			this.birthday = birthday;
			return true;
		} else {
			return false;
		}
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public boolean setPhoneNumber(String phoneNumber) {
		if (isValidPhoneNumber(phoneNumber)) {
			this.phoneNumber = phoneNumber;
			return true;
		} else {
			return false;
		}
	}

	public String getAddress() {
		return address;
	}

	public boolean setAddress(String address) {
		if (isValidAddress(address)) {
			this.address = address;
			return true;
		} else {
			return false;
		}
	}

	public String getGender() {
		return gender;
	}

	public boolean setGender(String gender) {
		this.gender = gender;
		return true;
	}

	public int getStatus() {
		return status;
	}

	public boolean setStatus(int status) {
		this.status = status;
		return true;
	}

	public String getID() {
		return ID;
	}

	private boolean promtName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter name: ");
		String name = sc.nextLine();
		while (this.setName(name) != true) {
			System.out.println("Invalid name format! Please re-enter name: ");
			name = sc.nextLine();
		}
		return true;
	}

	private boolean promtEmail() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter email: ");
		String email = sc.nextLine();
		while (this.setEmail(email) != true) {
			System.out.println("Invalid email format! Please re-enter email: ");
			email = sc.nextLine();
		}
		return true;
	}

	private boolean promtPassword() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter password (Enter 'q' to use default password 'User@1234'): ");
		String password = sc.nextLine();
		boolean isStop = false;
		while (isStop != true) {
			if (password.equalsIgnoreCase("q")) {
				this.setPassword("User@1234");
				isStop = true;
			} else {
				if (this.setPassword(password) == false) {
					System.out.println(
							"Password should have lenght >= 8, and contain at least 1 uppercase character, 1 special character!");
					System.out.println("Please re-enter password: ");
					password = sc.nextLine();
				} else {
					isStop = true;
				}
			}
		}
		return true;
	}

	private boolean promtBirthday() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter birthday (yyyy-MM-dd): ");
		String birthday = sc.nextLine();
		while (this.setBirthday(birthday) != true) {
			System.out.println("Invalid day format! Please re-enter email (yyyy-MM-dd): ");
			birthday = sc.nextLine();
		}
		return true;
	}

	private boolean promtPhoneNumber() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter phone number: ");
		String tel = sc.nextLine();
		while (this.setPhoneNumber(tel) != true) {
			System.out.println("Invalid phone number format! Please re-enter phone number: ");
			tel = sc.nextLine();
		}
		return true;
	}

	private boolean promtAddress() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter address: ");
		String address = sc.nextLine();
		while (this.setAddress(address) != true) {
			System.out.println("Invalid address format! Please re-enter address: ");
			address = sc.nextLine();
		}
		return true;
	}

	private boolean promtGender() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please choose gender: [M-Male, F-Female]");
		String gender = sc.nextLine();
		while (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F")) {
			System.out.println("Invalid gender! Please choose a gender: [M-Male, F-Female]");
			gender = sc.nextLine();
		}
		this.setGender(gender);
		return true;
	}

	private String genID() {
		String temp = this.getCurrentDatetime();
		temp = temp.replace("-", "");
		temp = temp.replace(":", "");
		temp = temp.replace(" ", "");
		return temp;
	}

	public void promtDetail() {
		String ID = this.genID();
		this.ID = ID;
		this.promtName();
		this.promtEmail();
		this.promtPassword();
		this.promtBirthday();
		this.promtPhoneNumber();
		this.promtAddress();
		this.promtGender();
	}

	private boolean isValidName(String name) {
		return name.length() >= 2 && name.length() <= 200;
	}

	private boolean isValidEmail(String email) {
		String regexEmail = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regexEmail);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	private boolean isValidPassword(String password) {
		String regexPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$\n";
		Pattern pattern = Pattern.compile(regexPassword);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

	private boolean isValidBirthday(String birthday) {
		String regexBirthday = "^\\d{4}-\\d{2}-\\d{2}$";
		Pattern pattern = Pattern.compile(regexBirthday);
		Matcher matcher = pattern.matcher(birthday);
		return matcher.matches();
	}

	private boolean isValidPhoneNumber(String phoneNumber) {
		String regexPhoneNumber = "^[0|81|84]+[1-9]{9,10}$";
		Pattern pattern = Pattern.compile(regexPhoneNumber);
		Matcher matcher = pattern.matcher(phoneNumber);
		return matcher.matches();
	}

	private boolean isValidAddress(String address) {
		return address.length() >= 3 && address.length() < 400;
	}

	private boolean repOk(String name, String email, String password, String birthday, String phoneNumber,
			String address) {
		return isValidName(name) && isValidEmail(email) && isValidPassword(password) && isValidBirthday(birthday)
				&& isValidPhoneNumber(phoneNumber) && isValidAddress(address);
	}

	@Override
	public String toString() {
		return "ID=" + ID + ", name=" + name + ", email=" + email + ", password=" + password + ", birthday=" + birthday
				+ ", phoneNumber=" + phoneNumber + ", address=" + address + ", gender=" + gender;
	}

}

class Student extends User {
	private String course;
	private int year;

	public Student(String ID, String name, String email, String password, String birthday, String phoneNumber,
			String address, String gender, String course, int year) throws Exception {
		super(ID, name, email, password, birthday, phoneNumber, address, gender);
		if (repOk(course, year)) {
			this.course = course;
			this.year = year;
		}
	}

	public Student() {

	}

	public String getCourse() {
		return course;
	}

	public boolean setCourse(String course) {
		if (isValidCourse(course)) {
			this.course = course;
			return true;
		} else {
			return false;
		}
	}

	public int getYear() {
		return year;
	}

	public boolean setYear(int year) {
		if (isValidYear(year)) {
			this.year = year;
			return true;
		} else {
			return false;
		}
	}

	public boolean promtCourse() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter course: ");
		String course = sc.nextLine();
		while (this.setCourse(course) != true) {
			System.out.println("Invalid course! Please re-enter course: ");
			course = sc.nextLine();
		}
		return true;
	}

	public boolean promtYear() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter year: ");
		int year = sc.nextInt();
		while (this.setYear(year) != true) {
			System.out.println("Invalid year! Please re-enter year: ");
			year = sc.nextInt();
		}
		return true;
	}

	public void promtEducationDetail() {
		this.promtCourse();
		this.promtYear();
	}

	private boolean isValidCourse(String course) {
		return course.length() >= 2 && course.length() <= 10;
	}

	private boolean isValidYear(int year) {
		return year >= 1 && year <= 8;
	}

	private boolean repOk(String course, int year) {
		return isValidCourse(course) && isValidYear(year);
	}

	@Override
	public String toString() {
		return super.toString() + ", course=" + course + ", year=" + year;
	}

}

class Admin extends User implements LibraryManagementTools, AdminTools {
	private ArrayList<Student> studentList;
	private ArrayList<Librarian> librarianList;
	private final int USER_TYPE_LIBRARIAN = 0;
	private final int USER_TYPE_STUDENT = 1;
	private String librarianFilePath;
	private String studentFilePath;

	public Admin() {
		studentList = new ArrayList<Student>();
		librarianList = new ArrayList<Librarian>();
		librarianFilePath = "librarian.dat";
		studentFilePath = "student.dat";
	}

	public Admin(ArrayList<Librarian> newLibrarianList) {
		librarianList = newLibrarianList;
		studentList = new ArrayList<Student>();
	}

	public Admin(ArrayList<Student> newStudentList, ArrayList<Librarian> newLibrarianList) {
		if (newStudentList.isEmpty()) {
			if (newLibrarianList.isEmpty()) {
				studentList = new ArrayList<Student>();
				librarianList = new ArrayList<Librarian>();
			} else {
				studentList = new ArrayList<Student>();
				librarianList = newLibrarianList;
			}
		} else {
			if (newLibrarianList.isEmpty()) {
				studentList = newStudentList;
				librarianList = new ArrayList<Librarian>();
			} else {
				studentList = newStudentList;
				librarianList = newLibrarianList;
			}
		}
	}

	public Admin(String ID, String name, String email, String password, String birthday, String phoneNumber,
			String address, String gender) {
		super(ID, name, email, password, birthday, phoneNumber, address, gender);
		studentList = new ArrayList<Student>();
		librarianList = new ArrayList<Librarian>();
		librarianFilePath = "librarian.dat";
		studentFilePath = "student.dat";
	}

	private int findLibrarianByID(String librarianID) {
		int index = -1;
		for (int i = 0; i < this.librarianList.size(); i++) {
			if (this.librarianList.get(i).getID().equalsIgnoreCase(librarianID)) {
				index = i;
			}
		}
		return index;
	}

	@Override
	public String createLibrarian() {
		Librarian lib = new Librarian();
		lib.promtDetail();
		this.librarianList.add(lib);
		return lib.getID();
	}

	@Override
	public boolean removeLibrarian(String librarianID) {
		int index = this.findLibrarianByID(librarianID);
		if (index >= 0) {
			this.librarianList.remove(index);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateLibrarian(String librarianID, Librarian librarian) {
		int index = this.findLibrarianByID(librarianID);
		if (index >= 0) {
			this.librarianList.set(index, librarian);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Librarian getLibrarianByID(String librarianID) {
		Librarian lib = null;
		for (int i = 0; i < this.librarianList.size(); i++) {
			if (this.librarianList.get(i).getID().equalsIgnoreCase(librarianID)) {
				lib = this.librarianList.get(i);
			}
		}
		return lib;
	}

	@Override
	public ArrayList<Librarian> getAllLibrarian() {
		return this.librarianList;
	}

	@Override
	public String createStudent() {
		Student stu = new Student();
		stu.promtDetail();
		stu.promtEducationDetail();
		this.studentList.add(stu);
		return stu.getID();
	}

	private int findStudentByID(String studentID) {
		int index = -1;
		for (int i = 0; i < this.studentList.size(); i++) {
			if (this.studentList.get(i).getID().equalsIgnoreCase(studentID)) {
				index = i;
			}
		}
		return index;
	}

	@Override
	public boolean removeStudent(String studentID) {
		int index = this.findStudentByID(studentID);
		if (index >= 0) {
			this.studentList.remove(index);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Student getStudentByID(String studentID) {
		Student stu = null;
		for (int i = 0; i < this.studentList.size(); i++) {
			if (this.studentList.get(i).getID().equalsIgnoreCase(studentID)) {
				stu = this.studentList.get(i);
			}
		}
		return stu;
	}

	@Override
	public boolean updateStudent(String studentID, Student student) {
		int index = this.findStudentByID(studentID);
		if (index >= 0) {
			this.studentList.set(index, student);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String createRoom() {
		// TODO
		return null;
	}

	@Override
	public boolean removeRoom(String roomID) {
		// TODO
		return false;
	}

	@Override
	public Room getRoomByID(String roomID) {
		// TODO
		return null;
	}

	@Override
	public boolean updateRoom(String roomID, Room room) {
		// TODO
		return false;
	}

	@Override
	public boolean addFacility(Facility facility) {
		// TODO
		return false;
	}

	@Override
	public Facility getFacilityByID(String facilityID) {
		// TODO
		return null;
	}

	@Override
	public boolean removeFacility(String facilityID) {
		// TODO
		return false;
	}

	@Override
	public boolean updateFacility(String facilityID, Facility facility) {
		// TODO
		return false;
	}

	@Override
	public boolean addBook(Book book) {
		// TODO
		return false;
	}

	@Override
	public Book getBookByID(String bookID) {
		// TODO
		return null;
	}

	@Override
	public boolean updateBookDetail(String bookID, Book book) {
		// TODO
		return false;
	}

	@Override
	public boolean removeBook(String bookID) {
		// TODO
		return false;
	}

	@Override
	public ArrayList<Student> getAllStudent() {
		return this.studentList;
	}

	@Override
	public ArrayList<Room> getAllRoom() {
		// TODO
		return null;
	}

	@Override
	public ArrayList<Facility> getAllFacility() {
		// TODO
		return null;
	}

	@Override
	public ArrayList<Book> getAllBook() {
		// TODO
		return null;
	}

}

class Librarian extends User implements LibraryManagementTools {

	private ArrayList<Student> studentList;

	public Librarian() {
		studentList = new ArrayList<Student>();
	}

	public Librarian(ArrayList<Student> newStudentList) {
		studentList = newStudentList;
	}

	public Librarian(String ID, String name, String email, String password, String birthday, String phoneNumber,
			String address, String gender) {
		super(ID, name, email, password, birthday, phoneNumber, address, gender);
		studentList = new ArrayList<Student>();
	}

	@Override
	public boolean addBook(Book book) {
		// TODO
		return false;
	}

	@Override
	public ArrayList<Student> getAllStudent() {
		return this.studentList;
	}

	@Override
	public ArrayList<Room> getAllRoom() {
		// TODO
		return null;
	}

	@Override
	public ArrayList<Facility> getAllFacility() {
		// TODO
		return null;
	}

	@Override
	public ArrayList<Book> getAllBook() {
		// TODO
		return null;
	}

	@Override
	public Book getBookByID(String bookID) {
		// TODO
		return null;
	}

	@Override
	public boolean updateBookDetail(String bookID, Book book) {
		// TODO
		return false;
	}

	@Override
	public boolean removeBook(String bookID) {
		// TODO
		return false;
	}

}

interface LibraryManagementTools {
	public boolean addBook(Book book);

	public Book getBookByID(String bookID);

	public boolean updateBookDetail(String bookID, Book book);

	public boolean removeBook(String bookID);

	public ArrayList<Student> getAllStudent();

	public ArrayList<Room> getAllRoom();

	public ArrayList<Facility> getAllFacility();

	public ArrayList<Book> getAllBook();
}

interface AdminTools {
	public String createLibrarian(); // return new Librarian ID

	public boolean removeLibrarian(String librarianID);

	public boolean updateLibrarian(String librarianID, Librarian librarian);

	public Librarian getLibrarianByID(String librarianID);

	public ArrayList<Librarian> getAllLibrarian();

	public String createStudent(); // return new Student ID

	public boolean removeStudent(String studentID);

	public Student getStudentByID(String studentID);

	public boolean updateStudent(String studentID, Student student);

	public String createRoom(); // return new Room ID

	public boolean removeRoom(String roomID);

	public Room getRoomByID(String roomID);

	public boolean updateRoom(String roomID, Room room);

	public boolean addFacility(Facility facility);

	public Facility getFacilityByID(String facilityID);

	public boolean removeFacility(String facilityID);

	public boolean updateFacility(String facilityID, Facility facility);
}

class Book {
	public Book() {

	}
}

class Room {
	public Room() {

	}
}

class Facility {
	public Facility() {

	}
}
