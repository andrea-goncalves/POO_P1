import greenfoot.Color;

public class Settings
{
    //Directions
    public static final int RIGHT = 1;
    public static final int LEFT = -1;

    // World 
    public static final int WORLD_WIDTH = 960;
    public static final int WORLD_HEIGHT = 540;
    public static final int LEVEL1_HEIGHT = 544;
    public static final int TILE_SIZE = 32;
    public static final int TILE_SOLID = 1;
    public static final int TILE_TRAP = 2;
    public static final int HUD_Y = 15;
    public static final int DIALOGUE_X = 480;
    public static final int DIALOGUE_Y = 450;
    public static final int FRAMES_PER_SECOND = 60;

    //Levels 
    public static final int LEVEL_1 = 1;
    public static final int LEVEL_2 = 2;
    public static final int LEVEL_3 = 3;
    public static final int BOSS_LEVEL = 4;
    public static final int LEVEL_COMPLETE_BONUS = 10;
    public static final int DEFAULT_UNLOCK_SCORE = 0;
    public static final int WHISTLE_UNLOCK_SCORE = 60;
    public static final int LIGHTS_UNLOCK_SCORE = 80;
    public static final int CLUE_POINTS = 20;
    public static final int CLUE_DEFAULT_POINTS = 10;

    //Powers
    public static final String POWER_NONE = "None";
    public static final String POWER_WHISTLE = "Whistle";
    public static final String POWER_LIGHTS = "Lights Off";
    public static final String POWER_SHIELD = "Shield";
    public static final int WHISTLE_RANGE = 250;
    public static final int LIGHTS_OFF_TIME = 300;
    public static final int LIGHTS_SHORT_SIGHT = 100;
    public static final int FALL_GAME_OVER_DELAY = 60;

    //GameCharacter
    public static final double ACCELERATION = 1;
    public static final double GLIDE_FACTOR = 0.25;
    public static final double GLIDE_MAX_SPEED = 3;
    public static final double MAX_FALL_SPEED = 15;
    public static final int MAX_PUSH = 40;
    public static final int GROUND_CHECK_DISTANCE = 1;
    public static final int INVULNERABLE_TIME = 60;
    public static final int BLINK_BASE = 177;
    public static final int BLINK_AMPLITUDE = 78;
    public static final int BLINK_CYCLES = 2;
    public static final int TRANSPARENCY_FADED = 100;
    public static final int TRANSPARENCY_OPAQUE = 255;

    //Agents 
    public static final int AGENT_LIVES = 5;
    public static final int AGENT_SPEED = 6;
    public static final int AGENT_JUMP = 16;
    public static final int DRIFT_SPEED = 3;
    public static final int POWER_COOLDOWN = 240;
    public static final int ATTACK_COOLDOWN = 20;
    public static final int ATTACK_DAMAGE = 10;
    public static final int ATTACK_FRAME_DELAY = 4;
    public static final int ATTACK_REACH = 20;
    public static final int SHIELD_DISTANCE = 45;
    public static final int WALK_FRAME_DELAY = 5;
    public static final int WALK_FRAMES = 4;
    public static final int JUMP_FRAMES = 7;
    public static final int ZIG_ATTACK_FRAMES = 3;
    public static final int ZAG_ATTACK_FRAMES = 7;

    //Enemies 
    public static final int ENEMY_ANIMATION_DELAY = 6;
    public static final int DOG_LIVES = 1;
    public static final int DOG_SPEED = 3;
    public static final int DOG_SLEEP_TIME = 300;
    public static final int DOG_WALK_FRAMES = 4;
    public static final int SOLDIER_LIVES = 1;
    public static final int SOLDIER_SPEED = 2;
    public static final int SOLDIER_SLEEP_TIME = 300;
    public static final int SOLDIER_SIGHT = 300;
    public static final int SOLDIER_SIGHT_HEIGHT = 60;
    public static final int SOLDIER_AIM_TIME = 30;
    public static final int SOLDIER_RELOAD_TIME = 90;
    public static final int SOLDIER_SHOOT_ANIM_TIME = 15;
    public static final int SOLDIER_BULLET_OFFSET = 10;
    public static final int SOLDIER_WALK_FRAMES = 4;
    public static final int SOLDIER_ATTACK_FRAMES = 9;

    //Bullets
    public static final int BULLET_SPEED = 10;
    public static final int BULLET_WIDTH = 14;
    public static final int BULLET_HEIGHT = 5;

    //Boss
    public static final int BOSS_LIVES = 1;
    public static final int BOSS_SPEED = 0;
    public static final int BOSS_MAX_HEALTH = 200;
    public static final int BOSS_STUN_DURATION = 180;
    public static final int BOSS_STUN_FRAME_DELAY = 8;
    public static final int BOSS_SHOOT_INTERVAL = 90;
    public static final int BOSS_SHOOT_FRAME_DELAY = 5;
    public static final int BOSS_SHOT_FRAME = 4;
    public static final int BOSS_SHIELD_DISTANCE = 9;
    public static final int BOSS_SHIELD_WIDTH = 8;
    public static final int BOSS_SHIELD_HEIGHT = 160;
    public static final int BOSS_GROUND_Y = 235;
    public static final int BOSS_PROJECTILE_SIZE = 20;
    public static final int BOSS_BULLET_SPEED = 7;
    public static final int BOSS_BULLET_OFFSET = 20;
    public static final int BOSS_ATTACK_FRAMES = 8;
    public static final int BOSS_STUN_FRAMES = 7;

    //Scenery 
    public static final int PLATFORM_SPEED = 2;
    public static final int PLATFORM_WAIT_TIME = 180;
    public static final int PLATFORM_RIDER_TOLERANCE = 4;
    public static final int CRUSH_SIZE = 64;
    public static final int CRUSH_DOWN_SPEED = 8;
    public static final int CRUSH_UP_SPEED = 2;
    public static final int CRUSH_WAIT_TIME = 60;
    public static final int CRUSH_REST_TIME = 120;
    public static final int TRAP_TOP_TOLERANCE = 5;

    //Screens
    public static final int MENU_INPUT_DELAY = 20;
    public static final int PAUSE_INPUT_DELAY = 20;
    public static final int GAME_OVER_INPUT_DELAY = 40;
    public static final int WIN_INPUT_DELAY = 60;
    public static final int STORY_INPUT_DELAY = 20;
    public static final int MUSIC_VOLUME = 50;

    public static final int SIZE_SMALL = 20;
    public static final int SIZE_NORMAL = 24;
    public static final int SIZE_MESSAGE = 26;
    public static final int SIZE_LARGE = 28;
    public static final int SIZE_SECTION = 30;
    public static final int SIZE_MENU_TITLE = 48;
    public static final int SIZE_TITLE = 56;

    //Story crawl
    public static final int STORY_LINE_SPACING = 40;
    public static final double STORY_SCROLL_SPEED = 1.0;
    public static final Color STORY_TEXT_COLOR = new Color(255, 215, 0);
    public static final int STORY_STAR_COUNT = 150;
    public static final int STORY_STAR_SIZE = 2;
    public static final int STORY_TITLE_SIZE = 40;
    public static final int STORY_TEXT_SIZE = 26;

    //Dialogue box
    public static final int DIALOGUE_WIDTH = 900;
    public static final int DIALOGUE_HEIGHT = 130;
    public static final int DIALOGUE_NAME_SIZE = 22;
    public static final int DIALOGUE_TEXT_SIZE = 17;
    public static final int DIALOGUE_TEXT_X = 20;
    public static final int DIALOGUE_NAME_Y = 30;
    public static final int DIALOGUE_TEXT_Y = 75;
    public static final int DIALOGUE_HINT_X = 680;
    public static final int DIALOGUE_HINT_Y = 115;
    public static final String DIALOGUE_HINT = "ENTER para continuar";

    //Keys 
    public static final String ZIG_LEFT = "a";
    public static final String ZIG_RIGHT = "d";
    public static final String ZIG_JUMP = "w";
    public static final String ZIG_POWER = "s";
    public static final String ZIG_ATTACK = "f";
    public static final String ZAG_LEFT = "left";
    public static final String ZAG_RIGHT = "right";
    public static final String ZAG_JUMP = "up";
    public static final String ZAG_POWER = "down";
    public static final String ZAG_ATTACK = "0";
    public static final String KEY_INTERACT = "e";
    public static final String KEY_PAUSE = "p";
    public static final String KEY_NEXT_DIALOGUE = "enter";
    public static final String KEY_CONFIRM = "space";
    public static final String KEY_CONTINUE = "c";
    public static final String KEY_STORY = "t";
    public static final String KEY_RETRY = "r";
    public static final String KEY_RESTART = "i";
    public static final String KEY_SAVE = "g";
    public static final String KEY_MENU = "m";

    //Images
    public static final String IMAGE_EXTENSION = ".png";
    public static final String ZIG_PREFIX = "zig";
    public static final String ZAG_PREFIX = "zag";
    public static final String WALK_SUFFIX = "_walk";
    public static final String JUMP_SUFFIX = "_jump";
    public static final String ATTACK_SUFFIX = "_attack";
    public static final String SHIELD_SUFFIX = "_shield.png";
    public static final String DOG_WALK_PREFIX = "dog_walk";
    public static final String SOLDIER_WALK_PREFIX = "soldier_walk";
    public static final String SOLDIER_ATTACK_PREFIX = "soldier_attack";
    public static final String BOSS_IDLE_IMAGE = "general_walk1.png";
    public static final String BOSS_ATTACK_PREFIX = "general_attack";
    public static final String BOSS_STUN_PREFIX = "general_stum";
    public static final String PLATFORM_IMAGE = "plataforma.png";
    public static final String DOOR_IMAGE = "door.png";
    public static final String COMPUTER_IMAGE = "computer.png";
    public static final String CRUSH_TOP_IMAGE = "crushWallTopo.png";
    public static final String CRUSH_BOTTOM_IMAGE = "crusherWall_down.png";

    //Sounds
    public static final String SOUND_WHISTLE = "whistle.wav";
    public static final String SOUND_LIGHTS = "lights.wav";
    public static final String SOUND_BARK = "bark.wav";
    public static final String SOUND_SHOT = "blast.wav";
    public static final String SOUND_BOSS_SHOT = "blast.wav";
    public static final String MUSIC_MENU = "spy.wav";

    // Files 
    public static final String SAVE_FILE = "savegame.txt";
    public static final int SAVE_LEVEL_LINE = 0;
    public static final int SAVE_SCORE_LINE = 1;
    public static final String CSV_SEPARATOR = ",";
    public static final String LEVEL1_CSV = "Colisao_fase1.csv";
    public static final String LEVEL2_CSV = "Colisao_fase2.csv";
    public static final String LEVEL3_CSV = "Colisao_fase3.csv";
    public static final String BOSS_CSV = "Colisao_boss.csv";
    public static final String LEVEL1_BACKGROUND = "fase1.png";
    public static final String LEVEL2_BACKGROUND = "fase2.png";
    public static final String LEVEL3_BACKGROUND = "fase3.png";
    public static final String BOSS_BACKGROUND = "faseBoss.png";


    // World (extra) 
    public static final int CELL_SIZE = 1;
    public static final boolean WORLD_BOUNDED = false;
    public static final Color SCREEN_BACKGROUND = new Color(20, 20, 30);
    public static final Color TRANSPARENT = new Color(0, 0, 0, 0);
    public static final int FALL_TIMER_OFF = -1;
    public static final int FALL_EDGE_MARGIN = 1;

    // HUD 
    public static final int HUD_HEIGHT = 30;
    public static final int HUD_TEXT_X = 10;
    public static final int HUD_TEXT_Y = 22;
    public static final String HUD_FONT_NAME = "Arial";
    public static final boolean HUD_FONT_BOLD = true;
    public static final boolean HUD_FONT_ITALIC = false;
    public static final int HUD_FONT_SIZE = 18;
    public static final String HUD_SEPARATOR = "     ";
    public static final String HUD_LIVES_LABEL = "Lives: ";
    public static final String HUD_SCORE_LABEL = "Score: ";
    public static final String HUD_POWER_SEPARATOR = ": ";
    public static final String HUD_READY = "READY";
    public static final String HUD_PROGRESS_SEPARATOR = "/";

    // Clue, darkness, shields, boss bar
    public static final int CLUE_SIZE = 24;
    public static final Color CLUE_FILL_COLOR = Color.YELLOW;
    public static final Color CLUE_BORDER_COLOR = Color.ORANGE;
    public static final Color DARKNESS_COLOR = new Color(0, 0, 0, 150);
    public static final int BOSS_SHIELD_HEALTH = 100;
    public static final int AGENT_SHIELD_WIDTH = 16;
    public static final int AGENT_SHIELD_HEIGHT = 64;
    public static final int BAR_WIDTH = 300;
    public static final int BAR_HEIGHT = 20;
    public static final int BAR_BORDER = 2;
    public static final int BAR_UNSET = -1;
    public static final Color BAR_BORDER_COLOR = Color.BLACK;
    public static final Color BAR_BACK_COLOR = Color.DARK_GRAY;
    public static final Color BAR_FILL_COLOR = Color.RED;

    // Level 1 positions 
    public static final int L1_ZIG_X = 25;
    public static final int L1_ZAG_X = 30;
    public static final int L1_AGENT_Y = 484;
    public static final int L1_DOOR1_X = 155;
    public static final int L1_DOOR1_Y = 190;
    public static final int L1_DOOR1_SECONDS = 2;
    public static final int L1_DOOR2_X = 910;
    public static final int L1_DOOR2_Y = 475;
    public static final int L1_DOOR2_SECONDS = 0;
    public static final int L1_PLATFORM_X = 770;
    public static final int L1_PLATFORM_Y = 335;
    public static final int L1_PLATFORM_TILES = -5;
    public static final int L1_COMPUTER_DOOR1_X = 600;
    public static final int L1_COMPUTER_DOOR1_Y = 484;
    public static final int L1_COMPUTER_PLATFORM_X = 50;
    public static final int L1_COMPUTER_PLATFORM_Y = 190;
    public static final int L1_COMPUTER_DOORS_X = 500;
    public static final int L1_COMPUTER_DOORS_Y = 100;
    public static final int L1_CLUE1_X = 160;
    public static final int L1_CLUE1_Y = 400;
    public static final int L1_CLUE2_X = 650;
    public static final int L1_CLUE2_Y = 100;
    public static final int L1_EXIT_X = 955;
    public static final int L1_EXIT_Y = 490;

    //Level 2 positions 
    public static final int L2_ZIG_X = 25;
    public static final int L2_ZAG_X = 30;
    public static final int L2_AGENT_Y = 484;
    public static final int L2_PLATFORM1_X = 31;
    public static final int L2_PLATFORM1_Y = 333;
    public static final int L2_PLATFORM1_TILES = -6;
    public static final int L2_PLATFORM2_X = 928;
    public static final int L2_PLATFORM2_Y = 333;
    public static final int L2_PLATFORM2_TILES = 5;
    public static final int L2_PLATFORM3_X = 512;
    public static final int L2_PLATFORM3_Y = 142;
    public static final int L2_PLATFORM3_TILES = 5;
    public static final int L2_DOOR_X = 450;
    public static final int L2_DOOR_Y = 475;
    public static final int L2_DOOR_SECONDS = 0;
    public static final int L2_COMPUTER_DOOR_X = 420;
    public static final int L2_COMPUTER_DOOR_Y = 100;
    public static final int L2_COMPUTER_PLATFORM1_X = 200;
    public static final int L2_COMPUTER_PLATFORM1_Y = 482;
    public static final int L2_COMPUTER_PLATFORM2_X = 700;
    public static final int L2_COMPUTER_PLATFORM2_Y = 482;
    public static final int L2_COMPUTER_PLATFORM3_X = 580;
    public static final int L2_COMPUTER_PLATFORM3_Y = 295;
    public static final int L2_DOG1_X = 512;
    public static final int L2_DOG1_Y = 295;
    public static final int L2_DOG2_X = 920;
    public static final int L2_DOG2_Y = 100;
    public static final int L2_CLUE_X = 31;
    public static final int L2_CLUE_Y = 300;
    public static final int L2_EXIT_X = 950;
    public static final int L2_EXIT_Y = 100;

    //Level 3 positions
    public static final int L3_ZIG_X = 25;
    public static final int L3_ZAG_X = 30;
    public static final int L3_AGENT_Y = 135;
    public static final int L3_DOOR_X = 800;
    public static final int L3_DOOR_Y = 30;
    public static final int L3_DOOR_SECONDS = 5;
    public static final int L3_COMPUTER_DOOR_X = 642;
    public static final int L3_COMPUTER_DOOR_Y = 40;
    public static final int L3_COMPUTER_PLATFORM_X = 585;
    public static final int L3_COMPUTER_PLATFORM_Y = 455;
    public static final int L3_PLATFORM1_X = 350;
    public static final int L3_PLATFORM1_Y = 490;
    public static final int L3_PLATFORM1_TILES = -4;
    public static final int L3_PLATFORM2_X = 448;
    public static final int L3_PLATFORM2_Y = 302;
    public static final int L3_PLATFORM2_TILES = 3;
    public static final int L3_CRUSH_LONG_X = 320;
    public static final int L3_CRUSH_LONG_Y = 35;
    public static final int L3_CRUSH_LONG_TILES = 2;
    public static final int L3_CRUSH_TILES = 1;
    public static final int L3_CRUSH_UPPER_Y = 207;
    public static final int L3_CRUSH_LOWER_Y = 357;
    public static final int[] L3_CRUSH_UPPER_X = {832, 704, 576, 96};
    public static final int[] L3_CRUSH_LOWER_X = {768, 640, 512};
    public static final int L3_SOLDIER1_X = 455;
    public static final int L3_SOLDIER1_Y = 135;
    public static final int L3_SOLDIER2_X = 900;
    public static final int L3_SOLDIER2_Y = 50;
    public static final int L3_CLUE1_X = 420;
    public static final int L3_CLUE1_Y = 80;
    public static final int L3_CLUE2_X = 500;
    public static final int L3_CLUE2_Y = 250;
    public static final int L3_EXIT_X = 870;
    public static final int L3_EXIT_Y = 450;

    //Boss level positions 
    public static final int BOSS_ZIG_X = 100;
    public static final int BOSS_ZAG_X = 160;
    public static final int BOSS_AGENT_Y = 223;
    public static final int BOSS_START_X = 894;
    public static final int BOSS_START_Y = 223;
    public static final int BOSS_BAR_X = 480;
    public static final int BOSS_BAR_Y = 510;

    //Screen layouts 
    public static final int MENU_TITLE_Y = 25;
    public static final int MENU_SUBTITLE_Y = 90;
    public static final int MENU_ZIG_X = 130;
    public static final int MENU_ZAG_X = 560;
    public static final int MENU_HEADER_Y = 150;
    public static final int MENU_FIRST_LINE_Y = 200;
    public static final int MENU_LINE_SPACING = 30;
    public static final int MENU_HELP_INTERACT_Y = 350;
    public static final int MENU_HELP_PAUSE_Y = 385;
    public static final int MENU_HELP_EXIT_Y = 420;
    public static final int MENU_HELP_GLIDE_Y = 450;
    public static final int MENU_OPTIONS_Y = 470;
    public static final int PAUSE_TITLE_Y = 90;
    public static final int PAUSE_CONTINUE_Y = 200;
    public static final int PAUSE_SAVE_Y = 250;
    public static final int PAUSE_MENU_Y = 300;
    public static final int PAUSE_MESSAGE_Y = 420;
    public static final int GAME_OVER_TITLE_Y = 120;
    public static final int GAME_OVER_TEXT_Y = 200;
    public static final int GAME_OVER_SCORE_Y = 240;
    public static final int GAME_OVER_RETRY_Y = 320;
    public static final int GAME_OVER_RESTART_Y = 370;
    public static final int WIN_TITLE_Y = 120;
    public static final int WIN_TEXT_Y = 210;
    public static final int WIN_SCORE_Y = 260;
    public static final int WIN_HINT_Y = 350;  

    private Settings() { }
}