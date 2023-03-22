package mod.acgaming.universaltweaks.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import mod.acgaming.universaltweaks.UniversalTweaks;
import mod.acgaming.universaltweaks.bugfixes.blockoverlay.UTBlockOverlayLists;
import mod.acgaming.universaltweaks.core.UTLoadingPlugin;
import mod.acgaming.universaltweaks.mods.botania.UTBotaniaFancySkybox;
import mod.acgaming.universaltweaks.tweaks.UTLoadSound;
import mod.acgaming.universaltweaks.tweaks.breakablebedrock.UTBreakableBedrock;
import mod.acgaming.universaltweaks.tweaks.incurablepotions.UTIncurablePotions;
import mod.acgaming.universaltweaks.tweaks.swingthroughgrass.UTSwingThroughGrassLists;
import mod.acgaming.universaltweaks.util.UTObsoleteModsScreenHandler;

@Config(modid = UniversalTweaks.MODID, name = "UniversalTweaks")
public class UTConfig
{
    @Config.LangKey("cfg.universaltweaks.bugfixes.blocks")
    @Config.Name("Bugfixes: Blocks")
    public static final BugfixesBlocksCategory BUGFIXES_BLOCKS = new BugfixesBlocksCategory();

    @Config.LangKey("cfg.universaltweaks.bugfixes.entities")
    @Config.Name("Bugfixes: Entities")
    public static final BugfixesEntitiesCategory BUGFIXES_ENTITIES = new BugfixesEntitiesCategory();

    @Config.LangKey("cfg.universaltweaks.bugfixes.misc")
    @Config.Name("Bugfixes: Misc")
    public static final BugfixesMiscCategory BUGFIXES_MISC = new BugfixesMiscCategory();

    @Config.LangKey("cfg.universaltweaks.bugfixes.world")
    @Config.Name("Bugfixes: World")
    public static final BugfixesWorldCategory BUGFIXES_WORLD = new BugfixesWorldCategory();

    @Config.LangKey("cfg.universaltweaks.debug")
    @Config.Name("Debug")
    public static final DebugCategory DEBUG = new DebugCategory();

    @Config.LangKey("cfg.universaltweaks.modintegration")
    @Config.Name("Mod Integration")
    public static final ModIntegrationCategory MOD_INTEGRATION = new ModIntegrationCategory();

    @Config.LangKey("cfg.universaltweaks.tweaks.blocks")
    @Config.Name("Tweaks: Blocks")
    public static final TweaksBlocksCategory TWEAKS_BLOCKS = new TweaksBlocksCategory();

    @Config.LangKey("cfg.universaltweaks.tweaks.entities")
    @Config.Name("Tweaks: Entities")
    public static final TweaksEntitiesCategory TWEAKS_ENTITIES = new TweaksEntitiesCategory();

    @Config.LangKey("cfg.universaltweaks.tweaks.items")
    @Config.Name("Tweaks: Items")
    public static final TweaksItemsCategory TWEAKS_ITEMS = new TweaksItemsCategory();

    @Config.LangKey("cfg.universaltweaks.tweaks.misc")
    @Config.Name("Tweaks: Misc")
    public static final TweaksMiscCategory TWEAKS_MISC = new TweaksMiscCategory();

    @Config.LangKey("cfg.universaltweaks.tweaks.performance")
    @Config.Name("Tweaks: Performance")
    public static final TweaksPerformanceCategory TWEAKS_PERFORMANCE = new TweaksPerformanceCategory();

    @Config.LangKey("cfg.universaltweaks.tweaks.world")
    @Config.Name("Tweaks: World")
    public static final TweaksWorldCategory TWEAKS_WORLD = new TweaksWorldCategory();

    public enum EnumLists
    {
        WHITELIST,
        BLACKLIST
    }

    public static class BugfixesBlocksCategory
    {
        @Config.LangKey("cfg.universaltweaks.bugfixes.blocks.blockoverlay")
        @Config.Name("Block Overlay")
        public final BlockOverlayCategory BLOCK_OVERLAY = new BlockOverlayCategory();

        @Config.Name("Comparator Timing")
        @Config.Comment("Fixes inconsistent delays of comparators to prevent redstone timing issues")
        public boolean utComparatorTimingToggle = true;

        @Config.Name("Hopper Bounding Box")
        @Config.Comment("Slims down the hopper bounding box for easier access of nearby blocks")
        public boolean utDietHopperToggle = true;

        @Config.Name("Hopper Insert Safety Check")
        @Config.Comment
            ({
                "Prevents crashes when the destination tile entity becomes unavailable during the item insert process",
                "Mainly utilized to suppress edge case symptoms with Thaumcraft's Thaumatorium"
            })
        public boolean utHopperInsertToggle = true;

        @Config.Name("Item Frame Void")
        @Config.Comment("Prevents voiding held items when right + left clicking on an item frame simultaneously")
        public boolean utItemFrameVoidToggle = true;

        @Config.Name("Ladder Flying Slowdown")
        @Config.Comment("Disables climbing movement when flying")
        public boolean utLadderFlyingToggle = true;

        @Config.Name("Mining Glitch")
        @Config.Comment("Avoids the need for multiple mining attempts by sending additional movement packets")
        public boolean utMiningGlitchToggle = true;

        @Config.Name("Piston Progress")
        @Config.Comment("Properly saves the last state of pistons to tags")
        public boolean utPistonTileToggle = true;

        public static class BlockOverlayCategory
        {
            @Config.Name("[1] Block Overlay Toggle")
            @Config.Comment("Fixes x-ray when standing in non-suffocating blocks")
            public boolean utBlockOverlayToggle = true;

            @Config.Name("[2] Blacklist")
            @Config.Comment
                ({
                    "Excludes blocks from the block overlay bugfix",
                    "Syntax: modid:block"
                })
            public String[] utBlockOverlayBlacklist = new String[] {};

            @Config.Name("[3] Whitelist")
            @Config.Comment
                ({
                    "Includes blocks in the block overlay bugfix",
                    "Syntax: modid:block"
                })
            public String[] utBlockOverlayWhitelist = new String[] {};
        }
    }

    public static class BugfixesEntitiesCategory
    {
        @Config.Name("Boat Riding Offset")
        @Config.Comment("Fixes entities glitching through the bottom of boats")
        public boolean utBoatOffsetToggle = true;

        @Config.Name("Death Time")
        @Config.Comment("Fixes corrupted entities exceeding the allowed death time")
        public boolean utDeathTimeToggle = true;

        @Config.Name("Destroy Entity Packets")
        @Config.Comment("Fixes lag caused by dead entities by sending additional packets when the player is not alive")
        public boolean utDestroyPacketToggle = true;

        @Config.Name("Disconnect Dupe")
        @Config.Comment("Fixes item dupes when players are dropping items and disconnecting")
        public boolean utDisconnectDupeToggle = true;

        @Config.Name("Dimension Change Player States")
        @Config.Comment("Fixes missing player states when changing dimensions by sending additional packets")
        public boolean utDimensionChangeToggle = true;

        @Config.Name("Entity Bounding Box")
        @Config.Comment("Saves entity bounding boxes to tags to prevent breakouts and suffocation")
        public boolean utEntityAABBToggle = true;

        @Config.Name("Entity Desync")
        @Config.Comment("Fixes entity motion desyncs most notable with arrows and thrown items")
        public boolean utEntityDesyncToggle = true;

        @Config.Name("Entity NaN Values")
        @Config.Comment("Prevents corruption of entities caused by invalid health or damage values")
        public boolean utEntityNaNToggle = true;

        @Config.Name("Entity Suffocation")
        @Config.Comment("Pushes entities out of blocks when growing up to prevent suffocation")
        public boolean utEntitySuffocationToggle = true;

        @Config.Name("Entity Tracker")
        @Config.Comment("Fixes entity tracker to prevent client-sided desyncs when teleporting or changing dimensions")
        public boolean utEntityTrackerToggle = true;

        @Config.Name("Entity UUID")
        @Config.Comment("Changes UUIDs of loaded entities in case their UUIDs are already assigned (and removes log spam)")
        public boolean utEntityUUIDToggle = true;

        @Config.Name("Max Player Health")
        @Config.Comment("Saves increased player health to tags")
        public boolean utMaxHealthToggle = true;

        @Config.Name("Player Saturation")
        @Config.Comment("Fixes saturation depleting in peaceful mode")
        public boolean utExhaustionToggle = true;

        @Config.Name("Skeleton Aim")
        @Config.Comment("Fixes skeletons not looking at their targets when strafing")
        public boolean utSkeletonAimToggle = true;

        @Config.Name("Villager Mantle Hoods")
        @Config.Comment("Returns missing hoods to villager mantles")
        public boolean utVillagerMantleToggle = true;
    }

    public static class BugfixesMiscCategory
    {
        @Config.LangKey("cfg.universaltweaks.bugfixes.misc.modelgap")
        @Config.Name("Model Gap")
        public final ModelGapCategory MODEL_GAP = new ModelGapCategory();

        @Config.Name("Block Fire")
        @Config.Comment("Prevents fire projectiles burning entities when blocking with shields")
        public boolean utBlockFireToggle = true;

        @Config.Name("Depth Mask")
        @Config.Comment("Fixes entity and particle rendering issues by enabling depth buffer writing")
        public boolean utDepthMaskToggle = true;

        @Config.Name("Faster Background Startup")
        @Config.Comment("Fixes slow background startup edge case caused by checking tooltips during the loading process")
        public boolean utFasterBackgroundStartupToggle = true;

        @Config.Name("Frustum Culling")
        @Config.Comment("Fixes invisible chunks in edge cases (small enclosed rooms at chunk borders)")
        public boolean utFrustumCullingToggle = true;

        @Config.Name("Help Command")
        @Config.Comment("Replaces the help command, sorts and reports broken commands")
        public boolean utHelpToggle = true;

        @Config.Name("Locale Crash")
        @Config.Comment("Prevents various crashes with Turkish locale")
        public boolean utLocaleToggle = true;

        public static class ModelGapCategory
        {
            @Config.Name("[1] Model Gap Toggle")
            @Config.Comment("Fixes transparent gaps in all 3D models of blocks and items")
            public boolean utModelGapToggle = true;

            @Config.Name("[2] Recess Value")
            @Config.Comment
                ({
                    "Quad X/Y offset",
                    "Moves the quad toward the center of the item",
                    "Use to hide gaps, keep as close to 0 as possible"
                })
            public double utModelGapRecess = 0.007D;

            @Config.Name("[3] Expansion Value")
            @Config.Comment
                ({
                    "Quad expansion increment",
                    "Enlarges each quad",
                    "Use to hide gaps, keep as close to 0 as possible"
                })
            public double utModelGapExpansion = 0.008D;
        }
    }

    public static class BugfixesWorldCategory
    {
        @Config.Name("Chunk Saving")
        @Config.Comment("Fixes loading of outdated chunks to prevent duplications, deletions and data corruption")
        public boolean utChunkSavingToggle = true;

        @Config.Name("Tile Entity Update Order")
        @Config.Comment("Keeps the order of tile entities on chunk load")
        public boolean utTELoadOrderToggle = true;
    }

    public static class TweaksBlocksCategory
    {
        @Config.LangKey("cfg.universaltweaks.tweaks.blocks.betterplacement")
        @Config.Name("Better Placement")
        public final BetterPlacementCategory BETTER_PLACEMENT = new BetterPlacementCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.blocks.breakablebedrock")
        @Config.Name("Breakable Bedrock")
        public final BreakableBedrockCategory BREAKABLE_BEDROCK = new BreakableBedrockCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.blocks.finitewater")
        @Config.Name("Finite Water")
        public final FiniteWaterCategory FINITE_WATER = new FiniteWaterCategory();

        @Config.Name("Bed Obstruction Replacement")
        @Config.Comment("Replaces bed obstruction checks with an improved version")
        public boolean utBedObstructionToggle = true;

        @Config.Name("Better Harvest")
        @Config.Comment("Prevents breaking lower parts of sugar cane and cacti as well as unripe crops, unless sneaking")
        public boolean utBetterHarvestToggle = false;

        @Config.Name("Cactus Size")
        @Config.Comment("Determines how tall cacti can grow")
        public int utCactusSize = 3;

        @Config.Name("Fast Leaf Decay")
        @Config.Comment("Makes leaves decay faster when trees are chopped")
        public boolean utLeafDecayToggle = true;

        @Config.Name("Fence/Wall Jump")
        @Config.Comment("Allows the player to jump over fences and walls")
        public boolean utFenceWallJumpToggle = true;
        @Config.Name("Hardcore Buckets")
        @Config.Comment("Prevents placing of liquid source blocks in the world")
        public boolean utHardcoreBucketsToggle = false;
        @Config.Name("Sugar Cane Size")
        @Config.Comment("Determines how tall sugar cane can grow")
        public int utSugarCaneSize = 3;

        public static class BetterPlacementCategory
        {
            @Config.Name("[1] Better Placement Toggle")
            @Config.Comment("Removes the delay between placing blocks")
            public boolean utBetterPlacementToggle = false;

            @Config.Name("[2] Force New Location")
            @Config.Comment("If the cursor must be moved to a new location before placing another block")
            public boolean utBetterPlacementNewLoc = true;

            @Config.Name("[3] Creative Mode Only")
            @Config.Comment("Only affects block placement in creative mode")
            public boolean utBetterPlacementCreative = false;
        }

        public static class BreakableBedrockCategory
        {
            @Config.Name("[1] Breakable Bedrock Toggle")
            @Config.Comment("Allows customizable mining of bedrock")
            public boolean utBreakableBedrockToggle = false;

            @Config.Name("[2] Tool List")
            @Config.Comment
                ({
                    "List of tools concerning mining bedrock",
                    "If empty, allows every tool to be used",
                    "Syntax: modid:tool"
                })
            public String[] utBreakableBedrockToolList = new String[] {};

            @Config.Name("[3] List Mode")
            @Config.Comment
                ({
                    "Blacklist Mode: Tools which can't mine bedrock, others can",
                    "Whitelist Mode: Tools which can mine bedrock, others can't"
                })
            public EnumLists utBreakableBedrockToolListMode = EnumLists.BLACKLIST;
        }

        public static class FiniteWaterCategory
        {
            @Config.Name("[1] Finite Water Toggle")
            @Config.Comment("Prevents creation of infinite water sources")
            public boolean utFiniteWaterToggle = false;

            @Config.Name("[2] Allow Water Biomes")
            @Config.Comment("Allows creation of infinite water sources in ocean and river biomes")
            public boolean utFiniteWaterWaterBiomes = true;

            @Config.Name("[3] Minimum Altitude")
            @Config.Comment("Inclusive minimum altitude at which water is infinite")
            public int utFiniteWaterInfMin = 0;

            @Config.Name("[4] Maximum Altitude")
            @Config.Comment("Inclusive maximum altitude at which water is infinite")
            public int utFiniteWaterInfMax = 63;
        }
    }

    public static class TweaksEntitiesCategory
    {
        @Config.LangKey("cfg.universaltweaks.tweaks.entities.attributes")
        @Config.Name("Attributes")
        public final AttributesCategory ATTRIBUTES = new AttributesCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.entities.betterburning")
        @Config.Name("Better Burning")
        public final BetterBurningCategory BETTER_BURNING = new BetterBurningCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.entities.easybreeding")
        @Config.Name("Easy Breeding")
        public final EasyBreedingCategory EASY_BREEDING = new EasyBreedingCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.entities.collisiondamage")
        @Config.Name("Collision Damage")
        public final CollisionDamageCategory COLLISION_DAMAGE = new CollisionDamageCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.entities.nogolems")
        @Config.Name("No Golems")
        public final NoGolemsCategory NO_GOLEMS = new NoGolemsCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.entities.playerspeed")
        @Config.Name("Player Speed")
        public final PlayerSpeedCategory PLAYER_SPEED = new PlayerSpeedCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.entities.waterfalldamage")
        @Config.Name("Water Fall Damage")
        public final WaterFallDamageCategory WATER_FALL_DAMAGE = new WaterFallDamageCategory();

        @Config.Name("AI Replacement")
        @Config.Comment("Replaces entity AI for improved server performance")
        public boolean utAIReplacementToggle = true;

        @Config.Name("AI Removal")
        @Config.Comment("Removes entity AI for improved server performance")
        public boolean utAIRemovalToggle = false;

        @Config.Name("Auto Jump Replacement")
        @Config.Comment("Replaces auto jump with an increased step height")
        public boolean utAutoJumpToggle = true;

        @Config.Name("Better Ignition")
        @Config.Comment("Enables ignition of entities by right-clicking instead of awkwardly lighting the block under them")
        public boolean utBetterIgnitionToggle = true;

        @Config.Name("Boat Speed")
        @Config.Comment("Sets the acceleration value for controlling boats")
        public double utBoatSpeed = 0.04D;

        @Config.Name("Creeper Confetti")
        @Config.Comment("Replaces deadly creeper explosions with delightful confetti")
        public boolean utCreeperConfettiToggle = false;

        @Config.Name("Charged Creeper Spawning Chance")
        @Config.Comment("Chance for creepers to spawn charged")
        @Config.RangeDouble(min = 0.0d, max = 1.0d)
        public double utChargedCreeperSpawnChance = 0.0d;

        @Config.Name("Disable Sleeping")
        @Config.Comment("Disables skipping night by using a bed while making it still able to set spawn")
        public boolean utSleepingToggle = false;

        @Config.Name("Husk & Stray Spawning")
        @Config.Comment("Lets husks and strays spawn underground like regular zombies and skeletons")
        public boolean utHuskStraySpawningToggle = true;

        @Config.Name("Mob Despawn Improvement")
        @Config.Comment("Mobs carrying picked up items will drop their equipment and despawn properly")
        public boolean utMobDespawnToggle = true;

        @Config.Name("No Saddled Wandering")
        @Config.Comment("Stops horses wandering around when saddled")
        public boolean utSaddledWanderingToggle = true;

        public static class AttributesCategory
        {
            @Config.Name("[01] Attributes Toggle")
            @Config.Comment("Sets custom ranges for entity attributes")
            public boolean utAttributesToggle = true;

            @Config.Name("[02] Max Health Min")
            public double utAttributeMaxHealthMin = -65536;

            @Config.Name("[03] Max Health Max")
            public double utAttributeMaxHealthMax = 65536;

            @Config.Name("[04] Follow Range Min")
            public double utAttributeFollowRangeMin = -65536;

            @Config.Name("[05] Follow Range Max")
            public double utAttributeFollowRangeMax = 65536;

            @Config.Name("[06] Knockback Resistance Min")
            public double utAttributeKnockbackResistanceMin = -65536;

            @Config.Name("[07] Knockback Resistance Max")
            public double utAttributeKnockbackResistanceMax = 65536;

            @Config.Name("[08] Movement Speed Min")
            public double utAttributeMovementSpeedMin = -65536;

            @Config.Name("[09] Movement Speed Max")
            public double utAttributeMovementSpeedMax = 65536;

            @Config.Name("[10] Flying Speed Min")
            public double utAttributeFlyingSpeedMin = -65536;

            @Config.Name("[11] Flying Speed Max")
            public double utAttributeFlyingSpeedMax = 65536;

            @Config.Name("[12] Attack Damage Min")
            public double utAttributeAttackDamageMin = -65536;

            @Config.Name("[13] Attack Damage Max")
            public double utAttributeAttackDamageMax = 65536;

            @Config.Name("[14] Attack Speed Min")
            public double utAttributeAttackSpeedMin = -65536;

            @Config.Name("[15] Attack Speed Max")
            public double utAttributeAttackSpeedMax = 65536;

            @Config.Name("[16] Armor Min")
            public double utAttributeArmorMin = -65536;

            @Config.Name("[17] Armor Max")
            public double utAttributeArmorMax = 65536;

            @Config.Name("[18] Armor Toughness Min")
            public double utAttributeArmorToughnessMin = -65536;

            @Config.Name("[19] Armor Toughness Max")
            public double utAttributeArmorToughnessMax = 65536;

            @Config.Name("[20] Luck Min")
            public double utAttributeLuckMin = -65536;

            @Config.Name("[21] Luck Max")
            public double utAttributeLuckMax = 65536;
        }

        public static class BetterBurningCategory
        {
            @Config.Name("[1] Cooked Items")
            @Config.Comment("Fixes some edge cases where fire damage sources won't cause mobs to drop their cooked items")
            public boolean utBBCookedToggle = true;

            @Config.Name("[2] Extinguishing")
            @Config.Comment("If entities have fire resistance, they get extinguished right away when on fire")
            public boolean utBBExtinguishToggle = true;

            @Config.Name("[3] Fire Overlay")
            @Config.Comment("Prevents the fire animation overlay from being displayed when the player is immune to fire")
            public boolean utBBOverlayToggle = true;

            @Config.Name("[4] Flaming Arrows")
            @Config.Comment("Allows skeletons to shoot flaming arrows when on fire (30% chance * regional difficulty")
            public boolean utBBArrowsToggle = true;

            @Config.Name("[5] Spreading Fire")
            @Config.Comment("Allows fire to spread from entity to entity (30% chance * regional difficulty)")
            public boolean utBBSpreadingToggle = true;
        }

        public static class EasyBreedingCategory
        {
            @Config.Name("[1] Easy Breeding Toggle")
            @Config.Comment("Enables easy breeding of animals by tossing food on the ground")
            public boolean utEasyBreedingToggle = false;

            @Config.Name("[2] Search Distance")
            @Config.Comment("Determines the distance for animals to search for food")
            public double utEasyBreedingDistance = 10;
        }

        public static class CollisionDamageCategory
        {
            @Config.Name("[1] Collision Damage Toggle")
            @Config.Comment("Applies horizontal collision damage to the player akin to elytra collision")
            public boolean utCollisionDamageToggle = false;

            @Config.Name("[2] Damage Factor")
            @Config.Comment
                ({
                    "The damage factor that gets multiplied with the player speed",
                    "Vanilla default for elytra damage is 10"
                })
            public int utCollisionDamageFactor = 10;
        }

        public static class NoGolemsCategory
        {
            @Config.Name("[1] Iron Golem Toggle")
            @Config.Comment("Disables the manual creation of iron golems")
            public boolean utNGIronGolemToggle = false;

            @Config.Name("[2] Snow Golem Toggle")
            @Config.Comment("Disables the manual creation of snow golems")
            public boolean utNGSnowGolemToggle = false;

            @Config.Name("[3] Wither Toggle")
            @Config.Comment("Disables the manual creation of withers")
            public boolean utNGWitherToggle = false;
        }

        public static class PlayerSpeedCategory
        {
            @Config.Name("[1] Player Speed Toggle")
            @Config.Comment("Enables the modification of base and maximum player speeds")
            public boolean utPlayerSpeedToggle = false;

            @Config.Name("[2] Walk Speed")
            @Config.Comment("Determines the player's base walk speed")
            public double utPlayerWalkSpeed = 0.1;

            @Config.Name("[3] Fly Speed")
            @Config.Comment("Determines the player's base fly speed")
            public double utPlayerFlySpeed = 0.05;

            @Config.Name("[4] Max Speed")
            @Config.Comment
                ({
                    "Determines the player's maximum speed",
                    "Increase if you get the infamous 'Player moved too quickly' messages"
                })
            public double utPlayerMaxSpeed = 100;

            @Config.Name("[5] Max Elytra Speed")
            @Config.Comment
                ({
                    "Determines the player's maximum speed when flying with elytra",
                    "Increase if you get the infamous 'Player moved too quickly' messages"
                })
            public double utPlayerMaxElytraSpeed = 300;

            @Config.Name("[6] Max Vehicle Speed")
            @Config.Comment
                ({
                    "Determines the player's maximum speed when riding a vehicle or mount",
                    "Increase if you get the infamous 'Player moved too quickly' messages"
                })
            public double utPlayerVehicleSpeed = 100;
        }

        public static class WaterFallDamageCategory
        {
            @Config.Name("[1] Water Fall Damage Toggle")
            @Config.Comment("Re-implements an improved version of pre-1.4 fall damage in water")
            public boolean utFallDamageToggle = false;

            @Config.Name("[2] Damage Reduction")
            @Config.Comment("How much fall damage gets reduced by water per tick")
            public float utFallDamageValue = 2.0F;
        }
    }

    public static class TweaksItemsCategory
    {
        @Config.LangKey("cfg.universaltweaks.tweaks.items.itementities")
        @Config.Name("Item Entities")
        public final ItemEntitiesCategory ITEM_ENTITIES = new ItemEntitiesCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.items.mending")
        @Config.Name("Mending")
        public final MendingCategory MENDING = new MendingCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.items.parry")
        @Config.Name("Shield Parry")
        public final ParryCategory PARRY = new ParryCategory();

        @Config.Name("Auto Switch Tools")
        @Config.Comment("Switches the selected hotbar slot to a proper tool if required")
        public boolean utAutoSwitchToggle = false;

        @Config.Name("No Attack Cooldown")
        @Config.Comment("Disables the 1.9 combat update attack cooldown")
        public boolean utAttackCooldownToggle = false;

        @Config.Name("Bow Infinity")
        @Config.Comment("Bows enchanted with Infinity no longer require arrows")
        public boolean utBowInfinityToggle = true;

        @Config.Name("Super Hot Torch")
        @Config.Comment("Enables one-time ignition of entities by hitting them with a torch")
        public boolean utSuperHotTorchToggle = false;

        @Config.Name("XP Bottle Amount")
        @Config.Comment
            ({
                "Sets the amount of experience spawned by bottles o' enchanting",
                "-1 for vanilla default"
            })
        public int utXPBottleAmount = -1;

        public static class ItemEntitiesCategory
        {
            @Config.Name("[01] Item Entities Toggle")
            @Config.Comment("Enables the modification of item entity properties")
            public boolean utItemEntitiesToggle = true;

            @Config.Name("[02] Physics")
            @Config.Comment("Adds physical aspects such as collision boxes to item entities")
            public boolean utIEPhysicsToggle = false;

            @Config.Name("[03] Automatic Pickup")
            @Config.Comment
                ({
                    "Item entities can be picked up automatically",
                    "When disabled, item entities can be picked up by right-clicking (requires 'Physics' option)"
                })
            public boolean utIEAutomaticPickupToggle = true;

            @Config.Name("[04] Sneaking Pickup")
            @Config.Comment("Item entities can only be picked up when sneaking")
            public boolean utIESneakingPickupToggle = false;

            @Config.Name("[05] Collection Tool")
            @Config.Comment
                ({
                    "Tools which enable picking up items automatically",
                    "Example -> minecraft:bucket"
                })
            public String[] utIECollectionTools = new String[] {};

            @Config.Name("[06] Pickup Delay")
            @Config.Comment
                ({
                    "Determines the delay in ticks until item entities can be picked up",
                    "-1 for vanilla default"
                })
            public int utIEPickupDelay = -1;

            @Config.Name("[07] Lifespan")
            @Config.Comment
                ({
                    "Determines the time in ticks until item entities get despawned",
                    "-1 for vanilla default"
                })
            public int utIELifespan = -1;

            @Config.Name("[08] No Combination")
            @Config.Comment("Stops combination of item entities")
            public boolean utIENoCombinationToggle = false;

            @Config.Name("[09] Smart Combination")
            @Config.Comment("Stops combination of item entities if their maximum stack size is reached")
            public boolean utIESmartCombinationToggle = true;

            @Config.Name("[10] Smart Combination Radius")
            @Config.Comment
                ({
                    "The radius (in blocks) that dropped items should check around them for other dropped items to combine with",
                    "Depends on the Smart Combination toggle"
                })
            public double utIESmartCombinationRadius = 2;

            @Config.Name("[11] Smart Combination Y-Axis Check")
            @Config.Comment
                ({
                    "Allows dropped items to also check above and below them for combination",
                    "Depends on the Smart Combination toggle"
                })
            public boolean utIESmartCombinationYAxis = true;

            @Config.Name("[12] Rotation")
            @Config.Comment("Enables the rotation effect")
            public boolean utIERotation = true;

            @Config.Name("[13] Bobbing")
            @Config.Comment("Enables the bobbing effect")
            public boolean utIEBobbing = true;
        }

        public static class MendingCategory
        {
            @Config.Name("[1] Mending Toggle")
            @Config.Comment("Implements modern mending behavior to only repair damaged equipment with XP")
            public boolean utMendingToggle = true;

            @Config.Name("[2] Ratio")
            @Config.Comment("Determines the amount of durability mending will repair, on average, per point of experience")
            public float utMendingRatio = 2.0F;

            @Config.Name("[3] Overpowered")
            @Config.Comment("Repairs damaged items from the entire inventory with XP")
            public boolean utMendingOPToggle = false;
        }

        public static class ParryCategory
        {
            @Config.Name("[1] Shield Parry Toggle")
            @Config.Comment("Allows parrying of projectiles with shields")
            public boolean utParryToggle = false;

            @Config.Name("[2] Arrow Time Window")
            @Config.Comment
                ({
                    "Determines the amount of time an arrow can be parried after raising the shield",
                    "Measured in ticks"
                })
            public int utParryArrowTimeWindow = 40;

            @Config.Name("[3] Fireball Time Window")
            @Config.Comment
                ({
                    "Determines the amount of time a fireball can be parried after raising the shield",
                    "Measured in ticks"
                })
            public int utParryFireballTimeWindow = 40;

            @Config.Name("[4] Throwable Time Window")
            @Config.Comment
                ({
                    "Determines the amount of time a throwable can be parried after raising the shield",
                    "Measured in ticks"
                })
            public int utParryThrowableTimeWindow = 40;

            @Config.Name("[5] Rebound Enchantment")
            @Config.Comment("Adds the Rebound enchantment for extended parry time windows")
            public boolean utParryReboundToggle = true;

            @Config.Name("[6] Rebound Treasure Enchantment")
            @Config.Comment("Makes the Rebound enchantment exclusive to enchanted books as loot")
            public boolean utParryReboundTreasure = false;

            @Config.Name("[7] Rebound Max Level")
            @Config.Comment("Maximum enchantment level for the Rebound enchantment")
            public int utParryReboundMaxLevel = 5;

            @Config.Name("[8] Rebound Multiplier")
            @Config.Comment("Multiplier for the parry time windows")
            public float utParryReboundMultiplier = 0.25F;
        }
    }

    public static class TweaksMiscCategory
    {
        @Config.LangKey("cfg.universaltweaks.tweaks.misc.incurablepotions")
        @Config.Name("Incurable Potions")
        public final IncurablePotionsCategory INCURABLE_POTIONS = new IncurablePotionsCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.misc.loadsounds")
        @Config.Name("Load Sounds")
        public final LoadSoundsCategory LOAD_SOUNDS = new LoadSoundsCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.misc.rallyhealth")
        @Config.Name("Rally Health")
        public final RallyHealthCategory RALLY_HEALTH = new RallyHealthCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.misc.smoothscrolling")
        @Config.Name("Smooth Scrolling")
        public final SmoothScrollingCategory SMOOTH_SCROLLING = new SmoothScrollingCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.misc.stg")
        @Config.Name("Swing Through Grass")
        public final SwingThroughGrassCategory SWING_THROUGH_GRASS = new SwingThroughGrassCategory();

        @Config.Name("Damage Tilt")
        @Config.Comment("Restores feature to tilt the camera when damaged")
        public boolean utDamageTiltToggle = true;

        @Config.Name("Disable Narrator")
        @Config.Comment("Disables the narrator functionality entirely")
        public boolean utDisableNarratorToggle = true;

        @Config.Name("End Portal Parallax")
        @Config.Comment("Re-implements parallax rendering of the end portal from 1.10 and older")
        public boolean utEndPortalParallaxToggle = true;

        @Config.Name("Infinite Music")
        @Config.Comment("Lets background music play continuously without delays")
        public boolean utInfiniteMusicToggle = false;

        @Config.Name("Linear XP Amount")
        @Config.Comment
            ({
                "Sets the amount of XP needed for each level, effectively removing the increasing level scaling",
                "0 for vanilla default"
            })
        public int utLinearXP = 0;

        @Config.Name("No Lightning Flash")
        @Config.Comment("Disables the flashing of skybox and ground brightness on lightning strikes")
        public boolean utLightningFlashToggle = false;

        @Config.Name("No Night Vision Flash")
        @Config.Comment("Disables the flashing effect when the night vision potion effect is about to run out")
        public boolean utNightVisionFlashToggle = false;

        @Config.Name("No Potion Shift")
        @Config.Comment("Disables the inventory shift when potion effects are active")
        public boolean utPotionShiftToggle = true;

        @Config.Name("No Redstone Lighting")
        @Config.Comment("Disables lighting of active redstone, repeaters, and comparators to improve performance")
        public boolean utRedstoneLightingToggle = false;

        @Config.Name("Offhand Improvement")
        @Config.Comment("Prevents placing offhand blocks when blocks or food are held in the mainhand")
        public boolean utOffhandToggle = true;

        @Config.Name("Remove Realms Button")
        @Config.Comment("Removes the redundant Minecraft Realms button from the main menu")
        public boolean utRealmsButtonToggle = true;

        @Config.Name("Remove Recipe Book")
        @Config.Comment("Removes the recipe book button from GUIs")
        public boolean utRecipeBookToggle = false;

        @Config.Name("Remove Snooper")
        @Config.Comment("Forcefully turns off the snooper and hides the snooper settings button from the options menu")
        public boolean utSnooperToggle = true;

        @Config.Name("Skip Credits")
        @Config.Comment("Skips the credits screen after the player goes through the end podium portal")
        public boolean utSkipCreditsToggle = false;

        @Config.Name("Suppress Tutorial Hints")
        @Config.Comment("Suppresses in-game tutorial hint tabs in the right-hand corner")
        public boolean utTutorialHintsToggle = true;

        @Config.Name("Toggle Cheats Button")
        @Config.Comment("Adds a button to the pause menu to toggle cheats")
        public boolean utToggleCheatsToggle = true;

        @Config.Name("Uncap FPS")
        @Config.Comment("Removes the hardcoded 30 FPS limit in screens like the main menu")
        public boolean utUncapFPSToggle = true;

        public static class IncurablePotionsCategory
        {
            @Config.Name("[1] Incurable Potions Toggle")
            @Config.Comment("Determines if potion effects are curable with curative items like buckets of milk")
            public boolean utIncurablePotionsToggle = true;

            @Config.Name("[2] Potion Effect List")
            @Config.Comment("Syntax: modid:potioneffect")
            public String[] utIncurablePotionsList = new String[] {};

            @Config.Name("[3] List Mode")
            @Config.Comment
                ({
                    "Blacklist Mode: Potion effects incurable by curative items, others are curable",
                    "Whitelist Mode: Potion effects curable by curative items, others are incurable"
                })
            public EnumLists utIncurablePotionsListMode = EnumLists.BLACKLIST;
        }

        public static class LoadSoundsCategory
        {
            @Config.Name("[1] Mode")
            @Config.Comment("Play load sound on...")
            public EnumSoundModes utLoadSoundMode = EnumSoundModes.NOTHING;

            @Config.Name("[2] Minecraft Loaded Sounds")
            @Config.Comment({"Sounds to play when Minecraft is loaded", "Syntax: eventname;pitch"})
            public String[] utLoadSoundMC = new String[]
                {
                    "entity.experience_orb.pickup;1.0",
                    "entity.player.levelup;1.0"
                };

            @Config.Name("[3] World Loaded Sounds")
            @Config.Comment({"Sounds to play when the world is loaded", "Syntax: eventname;pitch"})
            public String[] utLoadSoundWorld = new String[]
                {
                    "entity.experience_orb.pickup;1.0",
                    "entity.player.levelup;1.0"
                };

            public enum EnumSoundModes
            {
                NOTHING,
                MINECRAFT,
                WORLD,
                MINECRAFT_AND_WORLD
            }
        }

        public static class RallyHealthCategory
        {
            @Config.Name("[1] Rally Health Toggle")
            @Config.Comment
                ({
                    "Adds Bloodborne's Rally system to Minecraft",
                    "Regain lost health when attacking back within the risk time"
                })
            public boolean utRallyHealthToggle = false;

            @Config.Name("[2] Risk Time")
            @Config.Comment("Determines the risk time in ticks")
            public int utRallyHealthRiskTime = 60;

            @Config.Name("[3] Heal Chance")
            @Config.Comment("Determines the chance to regain health in percent")
            public int utRallyHealthHealChance = 80;

            @Config.Name("[4] Indication Sound")
            @Config.Comment("Plays an indication sound effect when health is regained")
            public boolean utRallyHealthSound = false;
        }

        public static class SmoothScrollingCategory
        {
            @Config.Name("[1] Smooth Scrolling Toggle")
            @Config.Comment("Adds smooth scrolling to in-game lists")
            public boolean utSmoothScrollingToggle = true;

            @Config.Name("[2] Bounce Back Multiplier")
            public double utSmoothScrollingBounce = 0.24;

            @Config.Name("[3] Scroll Duration")
            public int utSmoothScrollingDuration = 600;

            @Config.Name("[4] Scroll Step")
            public double utSmoothScrollingStep = 19.0;
        }

        public static class SwingThroughGrassCategory
        {
            @Config.Name("[1] Swing Through Grass Toggle")
            @Config.Comment("Allows hitting entities through grass instead of breaking it")
            public boolean utSwingThroughGrassToggle = true;

            @Config.Name("[2] Blacklist")
            @Config.Comment
                ({
                    "Excludes blocks from the swing through grass tweak",
                    "Syntax: modid:block"
                })
            public String[] utSwingThroughGrassBlacklist = new String[] {};

            @Config.Name("[3] Whitelist")
            @Config.Comment
                ({
                    "Includes blocks in the swing through grass tweak",
                    "Syntax: modid:block"
                })
            public String[] utSwingThroughGrassWhitelist = new String[] {};
        }
    }

    public static class TweaksPerformanceCategory
    {
        @Config.Name("Check Animated Models")
        @Config.Comment("Improves model load times by checking if an animated model exists before trying to load it")
        public boolean utCheckAnimatedModelsToggle = true;

        @Config.Name("Crafting Cache")
        @Config.Comment("Adds an IRecipe cache to improve recipe performance in larger modpacks")
        public boolean utCraftingCacheToggle = true;

        @Config.Name("Disable Audio Debug")
        @Config.Comment("Improves loading times by removing debug code for missing sounds and subtitles")
        public boolean utDisableAudioDebugToggle = true;

        @Config.Name("Fast Dye Blending")
        @Config.Comment("Replaces color lookup for sheep to check a predefined table rather than querying the recipe registry")
        public boolean utDyeBlendingToggle = true;

        @Config.Name("Fast Prefix Checking")
        @Config.Comment("Optimizes Forge's ID prefix checking and removes prefix warnings impacting load time")
        public boolean utPrefixCheckToggle = true;

        @Config.Name("Fast World Loading")
        @Config.Comment("Skips initial world chunk loading & garbage collection to speed up world loading")
        public boolean utWorldLoadingToggle = false;
    }

    public static class TweaksWorldCategory
    {
        @Config.LangKey("cfg.universaltweaks.tweaks.world.chunkgenlimit")
        @Config.Name("Chunk Gen Limit")
        public final ChunkGenLimitCategory CHUNK_GEN_LIMIT = new ChunkGenLimitCategory();
        @Config.LangKey("cfg.universaltweaks.tweaks.world.dimensionunload")
        @Config.Name("Dimension Unload")
        public final DimensionUnloadCategory DIMENSION_UNLOAD = new DimensionUnloadCategory();
        @Config.Name("Stronghold Replacement")
        @Config.Comment("Replaces stronghold generation with a safer variant")
        public boolean utStrongholdToggle = true;
        @Config.Name("Tidy Chunk")
        @Config.Comment("Tidies newly generated chunks by removing scattered item entities")
        public boolean utTidyChunkToggle = false;

        public static class ChunkGenLimitCategory
        {
            @Config.Name("[1] Chunk Gen Limit Toggle")
            @Config.Comment("Limits maximum chunk generation per tick for improved server performance")
            public boolean utChunkGenLimitToggle = false;

            @Config.Name("[2] Ticks")
            @Config.Comment("Maximum chunks to generate per tick per dimension")
            public int utChunkGenLimitTicks = 2;

            @Config.Name("[3] Time")
            @Config.Comment("Maximum time in ms to spend generating chunks per tick per dimension")
            public int utChunkGenLimitTime = 5;
        }

        public static class DimensionUnloadCategory
        {
            @Config.Name("[1] Dimension Unload Toggle")
            @Config.Comment("Unloads dimensions not in use to free up resources")
            public boolean utUnloaderToggle = true;

            @Config.Name("[2] Interval")
            @Config.Comment("Time (in ticks) to wait before checking dimensions")
            public int utUnloaderInterval = 600;

            @Config.Name("[3] Blacklist")
            @Config.Comment
                ({
                    "List of dimensions which should not be unloaded",
                    "Can be dimension name or ID",
                    "Uses regular expressions"
                })
            public String[] utUnloaderBlacklist = new String[]
                {
                    "0",
                    "overworld"
                };
        }
    }

    public static class DebugCategory
    {
        @Config.Name("Bypass Config Versioning")
        @Config.Comment
            ({
                "Disables config resets on mod updates",
                "Please note that you will face duplicate/invalid config settings"
            })
        public boolean utBypassConfigVersioningToggle = false;

        @Config.Name("Bypass Incompatibility Warnings")
        @Config.Comment("For those who live life on the edge, may or may not include Jons")
        public boolean utBypassIncompatibilityToggle = false;

        @Config.Name("Config Version")
        @Config.Comment
            ({
                "Version number of the config file",
                "Do not touch!",
            })
        public String utConfigVersion = UniversalTweaks.VERSION;

        @Config.Name("Debug Logging")
        @Config.Comment("Enables debug logging")
        public boolean utDebugToggle = false;

        @Config.Name("Obsolete Mods Screen")
        @Config.Comment("Enables a screen displaying incompatible mods on game load")
        public boolean utObsoleteModsToggle = true;

        @Config.Name("Show Loading Time")
        @Config.Comment("Prints the time the game needed to launch to the log")
        public boolean utLoadingTimeToggle = true;
    }

    public static class ModIntegrationCategory
    {
        @Config.LangKey("cfg.universaltweaks.modintegration.bop")
        @Config.Name("Biomes O' Plenty")
        public final BiomesOPlentyCategory BIOMES_O_PLENTY = new BiomesOPlentyCategory();

        @Config.LangKey("cfg.universaltweaks.modintegration.botania")
        @Config.Name("Botania")
        public final BotaniaCategory BOTANIA = new BotaniaCategory();

        @Config.LangKey("cfg.universaltweaks.modintegration.esm")
        @Config.Name("Epic Siege Mod")
        public final EpicSiegeModCategory EPIC_SIEGE_MOD = new EpicSiegeModCategory();

        @Config.LangKey("cfg.universaltweaks.modintegration.forestry")
        @Config.Name("Forestry")
        public final ForestryCategory FORESTRY = new ForestryCategory();

        @Config.LangKey("cfg.universaltweaks.modintegration.moc")
        @Config.Name("Mo' Creatures")
        public final MoCreaturesCategory MO_CREATURES = new MoCreaturesCategory();

        @Config.LangKey("cfg.universaltweaks.modintegration.roost")
        @Config.Name("Roost")
        public final RoostCategory ROOST = new RoostCategory();

        @Config.LangKey("cfg.universaltweaks.modintegration.sd")
        @Config.Name("Storage Drawers")
        public final StorageDrawersCategory STORAGE_DRAWERS = new StorageDrawersCategory();

        @Config.LangKey("cfg.universaltweaks.modintegration.tc")
        @Config.Name("Thaumcraft")
        public final ThaumcraftCategory THAUMCRAFT = new ThaumcraftCategory();

        @Config.LangKey("cfg.universaltweaks.modintegration.tcon")
        @Config.Name("Tinkers' Construct")
        public final TinkersConstructCategory TINKERS_CONSTRUCT = new TinkersConstructCategory();

        public static class BiomesOPlentyCategory
        {
            @Config.Name("Hot Spring Water")
            @Config.Comment("Fixes rapid inflection of regeneration effects in hot spring water")
            public boolean utBoPHotSpringWaterToggle = true;
        }

        public static class BotaniaCategory
        {
            @Config.Name("Fancy Skybox")
            @Config.Comment
                ({
                    "Enables the Botania Garden of Glass skybox for custom dimensions",
                    "Abides by Botania's 'enableFancySkybox' config option",
                    "Example: 43"
                })
            public Integer[] utBotaniaSkyboxDims = new Integer[] {};
        }

        public static class EpicSiegeModCategory
        {
            @Config.Name("Disable Digger AI Debug")
            @Config.Comment("Disables leftover debug logging inside the digger AI of the beta builds")
            public boolean utESMDiggerDebugToggle = true;
        }

        public static class ForestryCategory
        {
            @Config.Name("Arborist Villager Trades")
            @Config.Comment
                ({
                    "Adds custom emerald to germling trades to the arborist villager",
                    "Syntax:        level;emeralds_min;emeralds_max;germlings_min;germlings_max;type;complexity_min;complexity_max",
                    "level          Level when this trade becomes available (how much trading needs to be done)",
                    "emeralds_min   Lower random limit for emeralds",
                    "emeralds_max   Upper random limit for emeralds",
                    "germlings_min  Lower random limit for germlings",
                    "germlings_max  Upper random limit for germlings",
                    "type           Type of germling, can be either pollen or sapling",
                    "complexity_min Lower limit of allele complexity",
                    "complexity_max Upper limit of allele complexity",
                    "",
                    "Example for a level 5 trade for a single sapling with a complexity between 6 and 10, costing between 10 to 40 emeralds:",
                    "5;10;40;1;1;sapling;6;10"
                })
            public String[] utFOArboristDeals = new String[] {};

            @Config.Name("Disable Bee Damage Armor Bypass")
            @Config.Comment("Disables damage caused by bees bypassing player armor")
            public boolean utFOBeeDamageArmorBypassToggle = true;

            @Config.Name("Extra Trees: Gather Windfall")
            @Config.Comment("Allows Forestry farms to pick up ExtraTrees fruit")
            public boolean utFOGatherWindfallToggle = true;
        }

        public static class MoCreaturesCategory
        {
            @Config.Name("Custom Modded Biomes")
            @Config.Comment
                ({
                    "Adds support for modded biome spawns",
                    "Syntax: modid;name;key;tag;filename",
                    "modid    Mod ID required for this to be added",
                    "name     Mod name",
                    "key      Used for class lookups, needs to be a unique part of the modded package, e.g. mod.acgaming.*universaltweaks*.mods",
                    "tag      Short tag for logs",
                    "filename Filename to use for the generated config"
                })
            public String[] utMoCBiomeMods = new String[]
                {
                    "pvj;vibrantjourneys;vibrantjourneys;PVJ;ProjectVibrantJourneys.cfg",
                    "traverse;traverse;traverse;TRAV;Traverse.cfg",
                    "dimdoors;dimdoors;dimdoors;DD;DimDoors.cfg"
                };
        }

        public static class RoostCategory
        {
            @Config.Name("ContentTweaker: Early Register CT Chickens")
            @Config.Comment
                ({
                    "Improves load time by registering CT chickens early for Roost to detect them",
                    "Note: All CT chickens must be specified in \"Custom Chickens\" for this tweak to work!",
                    "Note: The .zs files creating custom chickens must be loaded with '#loader preinit', not '#loader contenttweaker'!"
                })
            public boolean utRoostEarlyRegisterCTChickens = true;

            @Config.Name("Custom Chickens")
            @Config.Comment
                ({
                    "Adds custom chickens from mods (e.g. ContentTweaker) to Roost's stock texture check",
                    "Syntax: name",
                    "name     Chicken name",
                })
            public String[] utRoostChickenMods = new String[] {};
        }

        public static class StorageDrawersCategory
        {
            @Config.Name("Item Handlers")
            @Config.Comment
                ({
                    "Fixes voiding of items when nearing full capacity",
                    "Fixes slotless item handler implementation not allowing the extraction from compacting item drawers with the vending upgrade",
                    "Caches the drawer controller tile to avoid getting the TE from the world every time a drawer slave is interacted with"
                })
            public boolean utSDItemHandlers = true;

            @Config.Name("Render Range")
            @Config.Comment
                ({
                    "Approximate range in blocks at which drawers render contained items",
                    "0 for default unlimited range"
                })
            public int utSDRenderRange = 0;
        }

        public static class ThaumcraftCategory
        {
            @Config.Name("Firebat Particles")
            @Config.Comment("Adds particles to firebats similar to legacy versions")
            public boolean utTCFirebatParticlesToggle = true;

            @Config.Name("Stable Thaumometer")
            @Config.Comment("Stops the thaumometer from bobbing rapidly when using it to scan objects")
            public boolean utTCStableThaumometerToggle = true;

            @Config.Name("Wisp Particles")
            @Config.Comment("Increases particle size of wisps similar to legacy versions")
            public boolean utTCWispParticlesToggle = true;
        }

        public static class TinkersConstructCategory
        {
            @Config.Name("Gaseous Fluids")
            @Config.Comment("Excludes gaseous fluids from being transferable via faucets")
            public boolean utTConGaseousFluidsToggle = false;

            @Config.Name("Projectile Despawning")
            @Config.Comment("Despawns unbreakable projectiles faster to improve framerates")
            public boolean utTConProjectileToggle = true;

            @Config.Name("Offhand Shuriken")
            @Config.Comment("Suppresses special abilities of long swords and rapiers when shurikens are wielded in the offhand")
            public boolean utTConShurikenToggle = true;

            @Config.Name("Ore Dictionary Cache")
            @Config.Comment("Caches all ore dictionary smelting recipes to speed up game loading")
            public boolean utTConOreDictCacheToggle = true;
        }
    }

    @Mod.EventBusSubscriber(modid = UniversalTweaks.MODID)
    public static class EventHandler
    {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
        {
            if (event.getModID().equals(UniversalTweaks.MODID))
            {
                ConfigManager.sync(UniversalTweaks.MODID, Config.Type.INSTANCE);
                if (TWEAKS_BLOCKS.BREAKABLE_BEDROCK.utBreakableBedrockToggle) UTBreakableBedrock.initToolList();
                if (TWEAKS_MISC.SWING_THROUGH_GRASS.utSwingThroughGrassToggle) UTSwingThroughGrassLists.initLists();
                if (TWEAKS_MISC.INCURABLE_POTIONS.utIncurablePotionsToggle) UTIncurablePotions.initPotionList();
                if (UTLoadingPlugin.isClient)
                {
                    if (BUGFIXES_BLOCKS.BLOCK_OVERLAY.utBlockOverlayToggle) UTBlockOverlayLists.initLists();
                    if (Loader.isModLoaded("botania")) UTBotaniaFancySkybox.initDimList();
                    if (TWEAKS_MISC.LOAD_SOUNDS.utLoadSoundMode != TweaksMiscCategory.LoadSoundsCategory.EnumSoundModes.NOTHING) UTLoadSound.initLists();
                }
                UTObsoleteModsScreenHandler.shouldDisplay = true;
                UniversalTweaks.LOGGER.info("Universal Tweaks config reloaded");
            }
        }
    }
}