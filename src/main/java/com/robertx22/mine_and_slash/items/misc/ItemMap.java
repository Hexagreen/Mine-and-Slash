package com.robertx22.mine_and_slash.items.misc;

import com.robertx22.mine_and_slash.data_generation.models.IAutoModel;
import com.robertx22.mine_and_slash.data_generation.models.ItemModelManager;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.dimensions.MapManager;
import com.robertx22.mine_and_slash.dimensions.blocks.TileMapPortal;
import com.robertx22.mine_and_slash.mmorpg.registers.common.BlockRegister;
import com.robertx22.mine_and_slash.saveclasses.dungeon_dimension.DungeonDimensionData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

import java.util.HashMap;

public class ItemMap extends Item implements IAutoLocName, IAutoModel {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    int rarity = 0;

    public ItemMap(int rarity) {
        super(new Properties());
        this.rarity = rarity;
    }

    @Override
    public void generateModel(ItemModelManager manager) {
        manager.generated(this);
    }

    public static boolean createMapPortal(BlockPos mapDevicePos, DimensionType type, BlockPos pos, World world, MapItemData data) {

        if (WorldUtils.isMapWorld(world)) {

        } else {

            if (data != null) {

                ChunkPos cpos = Load.world(MapManager.getWorld(type))
                    .getData()
                    .randomFree();

                String dungeonID = DungeonDimensionData.getId(cpos);

                Load.world(MapManager.getWorld(type))
                    .init(data, cpos);

                return summonPortal(mapDevicePos, data, world, pos, type, dungeonID);

            }
        }

        return false;
    }

    private static boolean summonPortal(BlockPos mapDevicePos, MapItemData map, World world, BlockPos pos, DimensionType type, String dungeonId) {

        spawnFrameBlock(world, pos.south());
        spawnFrameBlock(world, pos.north());
        spawnFrameBlock(world, pos.east());
        spawnFrameBlock(world, pos.west());

        spawnFrameBlock(world, pos.south()
            .east());
        spawnFrameBlock(world, pos.south()
            .west());
        spawnFrameBlock(world, pos.north()
            .east());
        spawnFrameBlock(world, pos.north()
            .west());

        return spawnPortalBlock(mapDevicePos, map, world, pos, type, dungeonId);

    }

    private static boolean spawnPortalBlock(BlockPos mapDevicePos, MapItemData map, World world, BlockPos pos, DimensionType type, String dungeonId) {
        Block block = world.getBlockState(pos)
            .getBlock();

        if (block.equals(Blocks.AIR) || block.equals(BlockRegister.MAP_PORTAL.get())) {

            world.setBlockState(pos, BlockRegister.MAP_PORTAL.get()
                .getDefaultState(), 2);
            TileMapPortal portal = new TileMapPortal();
            portal.onMapSacrificed(mapDevicePos, map, dungeonId);
            world.setTileEntity(pos, portal);

            return true;
        }
        return false;
    }

    private static Block FRAME_BLOCK = Blocks.COBBLESTONE;

    private static void spawnFrameBlock(World world, BlockPos pos) {

        Block block = world.getBlockState(pos)
            .getBlock();
        if (block.equals(Blocks.AIR) || block.equals(FRAME_BLOCK)) {

            world.setBlockState(pos, FRAME_BLOCK.getDefaultState(), 2);
        }
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Gear_Items;
    }

    @Override
    public String locNameLangFileGUID() {
        return this.getRegistryName()
            .toString();
    }

    @Override
    public String locNameForLangFile() {
        return Rarities.Maps.get(this.rarity)
            .Color() + "Adventure Map";
    }

    @Override
    public String GUID() {
        return "adventure_map";
    }
}