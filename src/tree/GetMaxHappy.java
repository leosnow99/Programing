package tree;


import java.util.List;

public class GetMaxHappy {
    private static class Employee {
        int happy;
        List<Employee> subordinates;
    }

    private static class ReturnType {
        int yesHeadMax;
        int noHeadMax;

        ReturnType(int yesHeadMax, int noHeadMax) {
            this.yesHeadMax = yesHeadMax;
            this.noHeadMax = noHeadMax;
        }
    }

    public static ReturnType process(Employee employee) {
        int yesEmployee = employee.happy;
        int noEmployee = 0;
        if (employee.subordinates == null) {
            return new ReturnType(yesEmployee, noEmployee);
        }
        for (Employee subordinate : employee.subordinates) {
            // 递归调用 process，得到以 next 为头节点的子树，
            //在 next 来和不来两种情况下分别获得的最大收益
            final ReturnType result = process(subordinate);

            noEmployee += Math.max(result.noHeadMax, result.yesHeadMax);
            yesEmployee += result.noHeadMax;
        }
        return new ReturnType(yesEmployee, noEmployee);
    }

    public static int getMaxHappy(Employee employee) {
        final ReturnType result = process(employee);
        return Math.max(result.noHeadMax, result.yesHeadMax);
    }
}
