import java.util.ArrayList;

/***************************************************
 * AccessPermissionsExample.java
 * Paul Parker
 * <p>
 * Demonstrates the use of enumerated values and short
 * cuts
 ****************************************************/
public class AccessPermissionsExample {
    public static void main(String[] args) {
        ArrayList<Access> allDepartmentsRights = new ArrayList<Access>(); // contains only all department access
        ArrayList<Access> ownDepartmentRights = new ArrayList<Access>(); // contains only own department access
        ArrayList<Access> allAndOwnDepartmentRights = new ArrayList<Access>(); // contains both all and own department access
        ArrayList<Access> noRights = new ArrayList<Access>(); // contains no access rights
        String research = "Research";
        String development = "Development";

        // load access lists
        allDepartmentsRights.add(Access.AllDepartments);
        ownDepartmentRights.add(Access.OwnDepartment);
        allAndOwnDepartmentRights.add(Access.AllDepartments);
        allAndOwnDepartmentRights.add(Access.OwnDepartment);

        // test all departments only
        System.out.println("Testing all departments access with same department");
        testAccess(allDepartmentsRights, research, research);
        System.out.println();
        System.out.println("Testing all departments access with different department");
        testAccess(allDepartmentsRights, research, development);

        System.out.println();
        System.out.println("--------------------");

        // test own departments only
        System.out.println("Testing own departments access with same department");
        testAccess(ownDepartmentRights, research, research);
        System.out.println();
        System.out.println("Testing own departments access with different department");
        testAccess(ownDepartmentRights, research, development);

        System.out.println();
        System.out.println("--------------------");

        // test own and all departments access
        System.out.println("Testing own and all departments access with same department");
        testAccess(allAndOwnDepartmentRights, research, research);
        System.out.println();
        System.out.println("Testing own and all departments access with different department");
        testAccess(allAndOwnDepartmentRights, research, development);

        System.out.println();
        System.out.println("--------------------");

        // test no departments access
        System.out.println("Testing no departments access with same department");
        testAccess(noRights, research, research);
        System.out.println();
        System.out.println("Testing no departments access with different department");
        testAccess(noRights, research, development);
    }

    /**
     * @param accessRights            access rights user has
     * @param userDepartment          department of the user
     * @param departmentBeingAccessed department being accessed
     */
    private static void testAccess(ArrayList<Access> accessRights, String userDepartment, String departmentBeingAccessed) {
        if (testAllAccess(accessRights) || (testOwnAccess(accessRights) && testSameDepartment(userDepartment, departmentBeingAccessed))) {
            System.out.printf("The user with department %1$s has access to %2$s%n", userDepartment, departmentBeingAccessed);
        } else {
            System.out.printf("The user with department %1$s does not have access to %2$s%n", userDepartment, departmentBeingAccessed);
        }
    }

    /**
     * @param accessRights access rights user has
     * @return whether the access list has all department access
     */
    private static boolean testAllAccess(ArrayList<Access> accessRights) {
        boolean hasAllAccess = accessRights.contains(Access.AllDepartments); // if user has all access
        if (hasAllAccess) {
            System.out.println("User had all access to departments and no further validation required");
        }
        return hasAllAccess;
    }

    private static boolean testSameDepartment(String userDepartment, String departmentBeingAccessed) {
        boolean isSameDepartment = userDepartment.compareToIgnoreCase(departmentBeingAccessed) == 0; // department matches
        if (isSameDepartment) {
            System.out.println("User did not have all access but does have own access and the department is the same");
        }
        return isSameDepartment;
    }

    /**
     * @param accessRights access rights user has
     * @return whether user has access to own department
     */
    private static boolean testOwnAccess(ArrayList<Access> accessRights) {
        boolean hasOwnAccess = accessRights.contains(Access.OwnDepartment); // if user has own access
        if (!hasOwnAccess) {
            System.out.println("User does not have access to own department no further validation required");
        }
        return hasOwnAccess;
    }

    /**
     * Enum describing basic user access to departments
     */
    private enum Access {
        AllDepartments, OwnDepartment
    }
}
