package oop.assigment_problems;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

/**
 * Assignment Problem 5: The Streaming Plan Renewal Reminder
 *
 * Demonstrates OOP polymorphism:
 * - Base abstract class Subscription defines common subscriber attributes (name, startDate)
 *   and abstract method getValidityDays() / calculateRenewalDate().
 * - Subclasses BasicSubscription (30 days), StandardSubscription (90 days), and
 *   PremiumSubscription (365 days) encapsulate their respective plan durations.
 * - SubscriptionFactory maps plan types to constructors without explicit if-else blocks.
 * - RenewalReminderSystem processes renewal date reminders polymorphically.
 */
public class L5_StreamingPlanRenewalReminder {

    /**
     * Abstract base class representing a streaming subscription.
     */
    public static abstract class Subscription {
        protected final String name;
        protected final LocalDate startDate;

        public Subscription(String name, LocalDate startDate) {
            this.name = name;
            this.startDate = startDate;
        }

        public String getName() {
            return name;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public abstract int getValidityDays();

        public LocalDate calculateRenewalDate() {
            return startDate.plusDays(getValidityDays());
        }
    }

    /**
     * Basic plan: valid for 30 days.
     */
    public static class BasicSubscription extends Subscription {
        public BasicSubscription(String name, LocalDate startDate) {
            super(name, startDate);
        }

        @Override
        public int getValidityDays() {
            return 30;
        }
    }

    /**
     * Standard plan: valid for 90 days.
     */
    public static class StandardSubscription extends Subscription {
        public StandardSubscription(String name, LocalDate startDate) {
            super(name, startDate);
        }

        @Override
        public int getValidityDays() {
            return 90;
        }
    }

    /**
     * Premium plan: valid for 365 days.
     */
    public static class PremiumSubscription extends Subscription {
        public PremiumSubscription(String name, LocalDate startDate) {
            super(name, startDate);
        }

        @Override
        public int getValidityDays() {
            return 365;
        }
    }

    /**
     * Functional interface for creating Subscription instances.
     */
    @FunctionalInterface
    public interface SubscriptionCreator {
        Subscription create(String name, LocalDate startDate);
    }

    /**
     * Factory class registering plan types without if-else checks.
     */
    public static class SubscriptionFactory {
        private static final Map<String, SubscriptionCreator> REGISTRY = new HashMap<>();

        static {
            REGISTRY.put("BASIC", BasicSubscription::new);
            REGISTRY.put("STANDARD", StandardSubscription::new);
            REGISTRY.put("PREMIUM", PremiumSubscription::new);
        }

        public static Subscription createSubscription(String planType, String name, LocalDate startDate) {
            SubscriptionCreator creator = REGISTRY.get(planType.toUpperCase(Locale.ROOT));
            if (creator == null) {
                throw new IllegalArgumentException("Unknown plan type: " + planType);
            }
            return creator.create(name, startDate);
        }
    }

    /**
     * Renewal reminder system processing all subscriptions polymorphically.
     */
    public static class RenewalReminderSystem {
        public static void processSubscriptions(List<Subscription> subscriptions) {
            for (Subscription subscription : subscriptions) {
                LocalDate renewalDate = subscription.calculateRenewalDate();
                System.out.println(subscription.getName() + ": " + renewalDate);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }
        int n = scanner.nextInt();
        List<Subscription> subscriptions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String planType = scanner.next();
            String name = scanner.next();
            String dateStr = scanner.next();
            LocalDate startDate = LocalDate.parse(dateStr);
            subscriptions.add(SubscriptionFactory.createSubscription(planType, name, startDate));
        }
        RenewalReminderSystem.processSubscriptions(subscriptions);
    }
}

/**
 * Alias class for direct execution without L5_ prefix.
 */
class StreamingPlanRenewalReminder extends L5_StreamingPlanRenewalReminder {
    public static void main(String[] args) {
        L5_StreamingPlanRenewalReminder.main(args);
    }
}