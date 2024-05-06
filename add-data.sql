insert into public.categories (id, name, description) values
    (1, 'Potions', 'Liquid substances with magic effects'),
    (2, 'Vestments', 'What magicians wear'),
    (3, 'Wands', 'Special magic wands to do some magic'),
    (4, 'Accessories', 'Miscellaneous magic attributes'),
    (5, 'Knights', 'A men of high social position trained to fight as a soldier on a horse'),
    (6, 'Princesses', 'A daughters of a king or queen'),
    (7, 'Beasts', 'Different magic animals'),
    (8, 'Weapons', 'Battle accessories serving protection purposes');

insert into public.suppliers (id, company_name, contact_person, phone, address) values
    (1, 'Mystic Brews & Potions Co.', 'Elara Moonwhisper', '+1234-567-890', '45 Cauldron Crescent, Enchanted Forest, EF 12345'),
    (2, 'Arcane Armaments Ltd.', 'Garrick Spellblade', '+2345-678-901', '88 Rune Road, Mystic Vale, MV 23456'),
    (3, 'Enchanted Vestments & Cloaks', 'Mirabel Starweave', '+3456-789-012', '332 Silk Street, Looming City, LC 34567'),
    (4, 'Grimm''s Beasts & Companions', 'Brom Grimm', '+4567-890-123', '101 Howl Avenue, Wild Woods, WW 45678'),
    (5, 'Royal Artifacts & Reliquaries', 'Lady Seraphina Blackwood', '+5678-901-234', '76 Crown Court, Kings Landing, KL 56789');

insert into public.order_statuses (id, name) values
     (1, 'Pending'), -- Customer started the checkout process, but it is not completed
     (2, 'Awaiting Payment'), -- The checkout process was completed, but payment is not confirmed yet
     (3, 'Awaiting Fulfillment'), -- The checkout process is completed and payment is confirmed
     (4, 'Awaiting Shipment'), -- Order is pulled and packaged and awaits shipping provider
     (5, 'Awaiting Pickup'), -- order awaits customer pickup from a seller-specific location
     (6, 'Partially Shipped'), -- only some items has been shipped
     (7, 'Completed'), -- payed, delivered, customer has got everything from order
     (8, 'Shipped'), -- order has been shipped, but receipt has not been confirmed;
     (9, 'Cancelled'), -- seller has cancelled an order (cancelling do not refund the order)
     (10, 'Declined'), -- seller has marked the order as declined
     (11, 'Refunded'), -- seller refunded the whole order
     (12, 'Disputed'), -- customer initiated a dispute
     (13, 'Manual Verification Required'), -- order on hold for some reason
     (14, 'Partially Refunded'); -- seller has partially refunded the order

insert into public.products (id, name, description, price, stock_quantity, category_id, supplier_id) values
     (1, 'Elixir of Health', 'Restores health and cures minor ailments.', 25.00, 50, 1, 1),
     (2, 'Invisibility Potion', 'Grants temporary invisibility.', 40.00, 30, 1, 1),
     (3, 'Wizards Robe', 'Enhanced with magical defenses.', 70.00, 15, 2, 3),
     (4, 'Sorcerer''s Hat', 'Increases magical potency.', 30.00, 25, 2, 3),
     (5, 'Elder Wand', 'One of the most powerful wands ever made.', 100.00, 10, 3, 2),
     (6, 'Willow Wand', 'Good for charm work.', 15.00, 40, 3, 2),
     (7, 'Crystal Ball', 'Used for scrying and seeing into the future.', 45.00, 20, 4, 5),
     (8, 'Magic Mirror', 'Answers whatever question it is asked.', 80.00, 5, 4, 5),
     (9, 'Don Quixote', 'The only one true knight.', 60.00, 10, 5, 4),
     (10, 'Princess Babblejam', 'a chatty princess in a pink dress', 300.00, 2, 6, 4),
     (11, 'Princess Peasedress', 'A princess in a green dress who loves flowers and nature', 300.00, 2, 6, 4),
     (12, 'Guardian Armor', 'A knight''s armor that shields the wearer from harm.', 90.00, 8, 8, 5),
     (13, 'Royal Crown', 'A beautiful crown fit for a princess.', 150.00, 4, 4, 5),
     (14, 'Silk Gown', 'A gown woven with threads of pure magic.', 120.00, 6, 2, 3),
     (15, 'Phoenix', 'A mythical bird that is reborn from its ashes.', 200.00, 2, 7, 4),
     (16, 'Griffin', 'A majestic creature with the body of a lion and wings of an eagle.', 180.00, 3, 7, 4),
     (17, 'Dragon Sword', 'Forged in dragon fire, capable of cutting through any armor.', 75.00, 15, 8, 2),
     (18, 'Enchanted Shield', 'A shield that returns any spells cast at it.', 65.00, 20, 8, 2),
     (19, 'Fireproof Cloak', 'Provides immunity to fire.', 50.00, 25, 2, 3),
     (20, 'Thunderbolt Staff', 'Can summon storms at will.', 85.00, 12, 3, 2),
     (21, 'Time Turner', 'Allows the user to travel back in time for short periods.', 160.00, 7, 4, 5),
     (22, 'Dragon', 'Usual dragon. Fire-breathing.', 150.00, 4, 7, 4);

insert into public.customers (id, firstname, lastname, email, phone, address) values
    (1, 'Loopa', 'Magicwand', 'loopa.magic@example.com', '+123-456-7890', '123 Wizard Way, Spelltown, ST 67890'),
    (2, 'Pupa', 'Spellcast', 'pupa.spell@example.com', '+234-567-8901', '456 Sorcery Street, Magicville, MV 78901'),
    (3, 'Arthur', 'Pendragon', 'arthur.pendragon@example.com', '+345-678-9012', '1 Camelot Drive, Knightdom, KD 89012'),
    (4, 'Robin', 'Hood', 'robin.hood@example.com', '+456-789-0123', '99 Sherwood Forest, Archer Park, AP 90123'),
    (5, 'Merlin', 'Magus', 'merlin.magus@example.com', '+567-890-1234', '321 Wizard Tower, Mystic Peak, MP 01234');

insert into public.orders (id, customer_id, ordered_at) values
    (1, 1, now()),
    (2, 2, now()),
    (3, 3, now()),
    (4, 4, now()),
    (5, 1, now());

insert into public.order_items (order_id, product_id, quantity, price) values
    (1, 1, 2, 25.00),  -- Order 1, Elixir of Health, 2 items, $25.00 each
    (1, 3, 1, 70.00),  -- Order 1, Wizards Robe, 1 item, $70.00 each
    (2, 5, 1, 100.00), -- Order 2, Elder Wand, 1 item, $100.00 each
    (3, 12, 2, 90.00), -- Order 3, Guardian Armor, 2 items, $90.00 each
    (4, 19, 1, 50.00), -- Order 4, Fireproof Cloak, 1 item, $50.00 each
    (5, 21, 1, 160.00),-- Order 5, Time Turner, 1 item, $160.00 each
    (5, 7, 1, 45.00);  -- Order 5, Crystal Ball, 1 item, $45.00 each


