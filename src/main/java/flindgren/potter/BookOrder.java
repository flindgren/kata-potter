package flindgren.potter;

import java.util.ArrayList;
import java.util.List;

public class BookOrder {

    private Book[] books;

    public BookOrder(Book[] books) {
        this.books = books;
    }

    public double price() {
        return makePackages().stream().mapToDouble(Package::price).sum();
    }

    private List<Package> makePackages() {
        List<Package> packages = packageSeries();
        return balancePackages(packages);
    }

    private List<Package> packageSeries() {
        List<Package> packages = new ArrayList<>();
        for (Book book : books) {
            if (!addToPackage(book, packages)) {
                Package aPackage = new Package();
                aPackage.accept(book);
                packages.add(aPackage);
            }
        }
        return packages;
    }

    private boolean addToPackage(Book book, List<Package> packages) {
        for (Package aPackage : packages) {
            if (aPackage.accept(book)) {
                return true;
            }
        }
        return false;
    }

    private List<Package> balancePackages(List<Package> packages) {
        List<Package> optimized = new ArrayList<>();
        for (Package aPackage : packages) {
            for (Package optimizedP : optimized) {
                optimizedP.balance(aPackage);
            }
            optimized.add(aPackage);
        }
        return optimized;
    }

}
