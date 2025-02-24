package org.openstack4j.openstack.storage.block.domain;

import java.util.Date;
import java.util.List;

import org.openstack4j.model.storage.block.Volume.Status;
import org.openstack4j.model.storage.block.VolumeSnapshot;
import org.openstack4j.model.storage.block.builder.VolumeSnapshotBuilder;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Objects;

/**
 * An OpenStack Volume Snapshot which is a point-in-time copy of a volume.
 *
 * @author Jeremy Unruh
 */
@JsonRootName("snapshot")
public class CinderVolumeSnapshot implements VolumeSnapshot {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String description;
	@JsonProperty("volume_id")
	private String volumeId;
	private Status status;
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT)
	@JsonProperty("size")
	private Integer size;
	@JsonProperty("created_at")
	private Date created;
	@JsonProperty
	private Boolean force;
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public VolumeSnapshotBuilder toBuilder() {
		return new ConcreteVolumeSnapshotBuilder(this);
	}
	
	/**
	 * @return a new Volume Snapshot builder
	 */
	public static VolumeSnapshotBuilder builder() {
		return new ConcreteVolumeSnapshotBuilder();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getVolumeId() {
		return volumeId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Status getStatus() {
		return status;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getSize() {
		return (size != null) ? size : 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getCreated() {
		return created;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues()
				     .add("id", id).add("name", name).add("description", description).add("volumeId", volumeId)
				     .add("status", status).add("created", created).add("force", force).add("size", size)
				     .toString();
	}

	public static class VolumeSnapshots extends ListResult<CinderVolumeSnapshot> {

		private static final long serialVersionUID = 1L;
		
		@JsonProperty("snapshots")
		private List<CinderVolumeSnapshot> snapshots;

		@Override
		protected List<CinderVolumeSnapshot> value() {
			return snapshots;
		}
	}
	
	public static class ConcreteVolumeSnapshotBuilder implements VolumeSnapshotBuilder {

		private CinderVolumeSnapshot m;
		
		ConcreteVolumeSnapshotBuilder() {
			this(new CinderVolumeSnapshot());
		}
		
		ConcreteVolumeSnapshotBuilder(CinderVolumeSnapshot m) {
			this.m = m;
		}
		
		@Override
		public VolumeSnapshotBuilder name(String name) {
			m.name = name;
			return this;
		}

		@Override
		public VolumeSnapshotBuilder description(String description) {
			m.description = description;
			return this;
		}

		@Override
		public VolumeSnapshotBuilder volume(String volumeId) {
			m.volumeId = volumeId;
			return this;
		}

		@Override
		public VolumeSnapshotBuilder force(boolean force) {
			m.force = force;
			return this;
		}

		@Override
		public VolumeSnapshot build() {
			return m;
		}

		@Override
		public VolumeSnapshotBuilder from(VolumeSnapshot in) {
			m = (CinderVolumeSnapshot) in;
			return this;
		}

	}
	
}
